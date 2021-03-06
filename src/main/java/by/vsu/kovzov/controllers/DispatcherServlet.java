package by.vsu.kovzov.controllers;

import by.vsu.kovzov.controllers.commands.Command;
import by.vsu.kovzov.controllers.commands.CommandFactory;
import by.vsu.kovzov.controllers.commands.CommandResult;
import by.vsu.kovzov.services.exceptions.ServiceException;
import by.vsu.kovzov.services.exceptions.TransactionException;
import by.vsu.kovzov.services.exceptions.ValidationException;
import by.vsu.kovzov.services.transaction.ServiceFactoryTransactionDecorator;
import by.vsu.kovzov.utils.ConnectionStorage;
import by.vsu.kovzov.utils.factories.ServiceFactory;
import by.vsu.kovzov.utils.factories.ServiceFactoryImpl;
import by.vsu.kovzov.utils.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;

public class DispatcherServlet extends HttpServlet {
    private ServiceFactory serviceFactory = new ServiceFactoryTransactionDecorator(new ServiceFactoryImpl());

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String context = req.getContextPath();
        String url = getUrl(req.getRequestURI(), context);

        Optional<Command> command = CommandFactory.getCommand(url);
        CommandResult result = null;
        if (command.isPresent()) {
            try (ConnectionStorage storage = ConnectionStorage.INSTANCE) {
                storage.setConnection(ConnectionPool.getInstance().getConnection());
                command.get().setServiceFactory(getServiceFactory());
                result = command.get().execute(req, resp);
            } catch (ValidationException | ServiceException | TransactionException e) {
                throw e;
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        processResult(result, url, req, resp);
    }

    @SneakyThrows
    private void processResult(CommandResult result, String url,
                               HttpServletRequest req, HttpServletResponse resp) {
        if (result == null || result.getUrl() == null) {
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
            return;
        }

        switch (result.getType()) {
            case REDIRECT: {
                String context = req.getContextPath();
                resp.sendRedirect(context + result.getUrl());
                return;
            }
            case FORWARD: {
                req.getRequestDispatcher(result.getUrl()).forward(req, resp);
                return;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + result.getType());
        }
    }

    private String getUrl(String uri, String context) {
        int postfixIndex = uri.lastIndexOf(".html");
        if (postfixIndex != -1) {
            uri = uri.substring(context.length(), postfixIndex);
        } else {
            uri = uri.substring(context.length());
        }
        return uri;
    }

    private ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
