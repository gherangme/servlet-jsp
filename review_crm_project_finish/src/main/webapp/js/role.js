$(document).ready(function(){
    //Khi toàn bộ nội dung được load xong thì chạy dòng này
    //.ten_class #ten_id
    $('.btn-xoa').click(function(e){
    e.preventDefault()
        //logic code
        //${this} đại diện cho chính element mà mình đang click
        const id = $(this).attr('id')
        const This = $(this)

        //Chức năng thêm dòng html
//        const data = {id: 15, role: 'test', desc: 'Đây là mô tả'}
//        const html = `<tr role="row" class="odd">
//                            <td class="sorting_1">${data.id}</td>
//                            <td>${data.role}</td>
//                            <td>${data.desc}</td>
//                            <td>
//                                <a href="#" class="btn btn-sm btn-primary">Sửa</a>
//                                <a href="#" class="btn btn-sm btn-danger btn-xoa" id=${data.id}>Xóa</a>
//                            </td>
//                      </tr>`
//
//        $("#example").find('tbody').append(html)

        //Chức năng chính
        $.ajax({
            method: 'GET',
            url: `http://localhost:8080/api/roles/delete?id=${id}`,
//            data:
        }).done(function(data){
            if(data.data) {
                This.closest('tr').remove()
            }else {
                console.log('Xóa thất bại !')
            }
        })
    })

    //Đăng ký click add
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

    //add user
    $('#btn-add-user').click(function(e){
        e.preventDefault()
        const fullName = $('#full-name').val()
        const email = $('#user-email').val()
        const password = $('#user-password').val()
        const role_id = $('#user-role-id').val()

        $.ajax({
            method: 'POST',
            url: `http://localhost:8080/api/users/add`,
            data: {
                'fullname': fullName,
                'email': email,
                'password': password,
                'role_id': role_id
            }
        }).done(function(data){
            if(data.data) {
                alert('Thêm user thành công')
            }else {
                console.log('Thêm user thất bại !')
            }
        })
    })

//Check POST sau
//    $('.btn-delete-user').click(function(e){
//        e.preventDefault()
//            //logic code
//            //${this} đại diện cho chính element mà mình đang click
//            const id = $(this).attr('id')
//            const This = $(this)
//
//            //Chức năng chính
//            $.ajax({
//                method: 'POST',
//                url: `http://localhost:8080/api/user/delete`,
//                data: {
//                    'id': id
//                }
//            }).done(function(data){
//                if(data.data) {
//                    This.closest('tr').remove()
//                }else {
//                    console.log('Xóa thất bại !')
//                }
//            })
//        })

    $('.btn-xoa-user').click(function(e){
        e.preventDefault()
            //logic code
            //${this} đại diện cho chính element mà mình đang click
            const id = $(this).attr('id')
            const This = $(this)
            $.ajax({
                method: 'GET',
                url: `http://localhost:8080/api/users/delete?id=${id}`,
    //            data:
            }).done(function(data){
                if(data.data) {
                    This.closest('tr').remove()
                }else {
                    console.log('Xóa thất bại !')
                }
            })
        })
        $('#btn-add-project').click(function(e){
                e.preventDefault()
                const name = $('#name-project').val()
//                const startDate = new Date()
//                const endDate = new Date()
                const startDate = $('#start-date').val()
                const endDate = $('#end-date').val()

                $.ajax({
                    method: 'POST',
                    url: `http://localhost:8080/api/projects/add`,
                    data: {
                    //Tên trong '' giống tên id(hoặc class) trong '' ở dòng 134 135
                        'name-project': name,
                        'start-date': startDate,
                        'end-date': endDate
                    }
                }).done(function(data){
                    if(data.data) {
                        alert('Thêm project thành công')
                    }else {
                        console.log('Thêm project thất bại !')
                    }
                })
    })
    $('.btn-delete-project').click(function(e){
                    e.preventDefault()
                        //logic code
                        //${this} đại diện cho chính element mà mình đang click
                        const id = $(this).attr('id')
                        const This = $(this)
                        $.ajax({
                            method: 'GET',
                            url: `http://localhost:8080/api/projects/delete?id=${id}`,
                //            data:
                        }).done(function(data){
                            if(data.data) {
                                This.closest('tr').remove()
                            }else {
                                console.log('Xóa thất bại !')
                            }
                        })
                    })
})