/*

 * index.js에서 var main = {}이라는 코드를 사용하는 이유
 * 여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름이 자주 발생할 수 있는데, 이런 문제를 피하기 위해 유효범위(scope) 사용
 * => index 객체 안에서만 function이 유효

*/

var main = {
    init : function() {
        var _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
    },

    save : function() {
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
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();