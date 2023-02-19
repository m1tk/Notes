$(document).ready(function () {
    $("#forgetBtn").click(function () {
        $("#dTab li:eq(2) a").tab("show");
        $(".tab-content div.active").removeClass("active in");
        $(".tab-content").find("#pane3").addClass("active in");
    });
    $("#loginBtn").click(function () {
        $("#dTab li:eq(1) a").tab("show");
        $(".tab-content div.active").removeClass("active in");
        $(".tab-content").find("#pane2").addClass("active in");
    });
});
