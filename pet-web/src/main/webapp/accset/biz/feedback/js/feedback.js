$(function () {

    // update feedback status
    $("button.btn_status").click(function () {
        var feedbackId = $(this).data("id");
        var status = $(this).data("status");
        $.ajax({
            type: "POST",
            url: "/coros/feedback/status/update.do",
            data: {id: feedbackId, status: status},
            success: function (result) {
                // if (result == "SUCCESS") {
                if (result.result == "0000") {
                    $("button[data-id=" + feedbackId + "]").removeAttr('disabled');
                    $("button[data-id=" + feedbackId + "][data-status=" + status + "]").attr('disabled', 'disabled');
                    $("#feedbackForm").submit();
                }
            }
        });
    });

    // clik show info
    $(".link_desc").click(function () {
        var id = $(this).data("id");
        var tr = $($(this).parent()[0]).parent()[0];
        var devInfo = $($(tr).children("td")[0]).html();
        var descInfo = $($(tr).children("td")[1]).text();
        var phoneInfo = $($(tr).children("td")[2]).html();
        var time = $($(tr).children("td")[4]).text();
        $("#dlg_desc").html(descInfo);
        $("#dlg_dev").html(devInfo);
        $("#dlg_phone").html(phoneInfo);
        $("#dlg_time").html(time);
        //status
        var status = $("button[data-id=" + id + "][disabled=disabled]").data('status');
        $(".modal .btn_status").attr("data-id", id);
        $(".modal .btn_status[data-id=" + id + "]").removeAttr('disabled');
        $(".modal .btn_status[data-status=" + status + "]").attr("disabled", "disabled");

    });

    // set date control
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    }).on('changeDate', function (ev) {
        var startTime = $("#starttime").val();
        var endTime = $("#endtime").val();
        if (endTime != '' && startTime != '') {
            $("#feedbackForm").submit();
        }
    });
    //set status default show
    var status = $("#hi_status").val();
    $('#tabs_filter a[data-status=' + status + ']').tab('show');

    //tab 点击事件 切换选项
    $('.nav-tabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });
    // filter click
    $('#tabs_filter a').click(function (e) {
        var status = $(e.target).data("status");
        $("#hi_status").val(status);
        $("#feedbackForm").submit();
    });

    //set pagination
    var totalPages = parseInt($("#totalPage").val());
    var nowPage = parseInt($("#pageNum").val());
    $('#pagination').twbsPagination({
        first: '首页',
        prev: '前一页',
        next: '下一页',
        last: '尾页',
        startPage: nowPage,
        totalPages: totalPages,
        visiblePages: 7,
        onPageClick: function (event, page) {
            var nowPage = parseInt($("#pageNum").val());
            if (page != nowPage) {
                $("#pageNum").val(page);
                $("#feedbackForm").submit();
            }
        }
    });

});