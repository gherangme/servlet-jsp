$(document).ready(function(){

    $('#btn-add').click(function(e){
        e.preventDefault()
        const name = $('#name').val()
        const desc = $('#desc').val()

        $.ajax({
            method: 'POST',
            url: `http://localhost:8080/api/roles/add`,
            data: {
                'name': name,
                'desc': desc
            }
        }).done(function(data){
            if(data.data) {
               alert('Thêm thành công')
            }else {
               console.log('Thêm thất bại !')
            }
        })
    })


})