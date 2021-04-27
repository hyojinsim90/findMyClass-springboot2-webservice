var main = {
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () { /*btn-update란 id를 가진 HTML엘리먼트에 이벤트가 발생할 때 update function을 실행하도록 이벤트 등록*/
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href='/'; /* 글 등록이 성공하면 메인페이지로 이동*/
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        }

        var id = $('#id').val();

        $.ajax({
            type: 'PUT', // PostsApiController의 API에서 이미 @PutMapping으로 선언했기 때문에 put을 사용해야함
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();
        $.ajax({
            type: 'DELETE', // PostsApiController의 API에서 이미 @PutMapping으로 선언했기 때문에 put을 사용해야함
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();
/* 이렇게 하는 이유 : main객체 안에서만 function이 유효하므로 다른 js와 겹칠 위험이 사라짐.
(나중에 로딩된 js의 init,save가 먼저 로딩된 js의 function을 덮어씀*/