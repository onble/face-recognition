const domain = "http://localhost:8080/OA";

// ---------------登录和注销模块----------------------
function auth() {
    // 获得session中保存的用户信息
    const staff_id = sessionStorage.getItem("staff_id");
    const staff_account = sessionStorage.getItem("staff_account");
    // 登录认证
    if (staff_id == null) {
        // 跳转到登录画面
        location.replace("./login.html");
    }
    // 动态设置用户名
    console.log($(".account"));
    $(".account").each(function (index, accountText) {
        $(accountText).text(staff_account);
    });
    // $("#account").text(staff_account);
}
auth();

function logout() {
    // 退出函数
    // 删除用户信息
    sessionStorage.removeItem("staff_account");
    sessionStorage.removeItem("staff_id");
    // 跳转到登录画面
    location.replace("./login.html");
}
