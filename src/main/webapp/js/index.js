$('th').on('click', function () {
    const params = new URLSearchParams(window.location.search);

    const column = $(this).data('column') || ''
    let order = params.get('order') || 'asc'

    if (order == 'DESC') {
        order = 'ASC'
    } else {
        order = 'DESC'
    }

    params.set('column', column)
    params.set('order', order)
    window.location.search = params;
})
