const domain = "http://localhost:8080/OA";

// ---------------登录和注销模块----------------------
function auth() {
    // 获得session中保存的用户信息
    const admin_id = sessionStorage.getItem("admin_id");
    const admin_account = sessionStorage.getItem("admin_account");
    // 登录认证
    if (admin_id == null) {
        // 跳转到登录画面
        location.replace("./login_admin.html");
    }
    // 动态设置用户名
    console.log($(".account"));
    $(".account").each(function (index, accountText) {
        $(accountText).text(admin_account);
    });
    // $("#account").text(admin_account);
}
auth();

// function logout() {
//     // 退出函数
//     // 删除用户信息
//     sessionStorage.removeItem("admin_account");
//     sessionStorage.removeItem("admin_id");
//     // 跳转到登录画面
//     location.replace("./login_admin.html");
// }
