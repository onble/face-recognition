const domain = "http://localhost:8080/OA";

// ---------------登录和注销模块----------------------
function auth() {
    // 获得session中保存的用户信息
    const staff_id = sessionStorage.getItem("staff_id");
    const staff_account = sessionStorage.getItem("staff_account");
    // 登录认证
    if (staff_id == null) {
        // 跳转到登录画面
        location.replace("./login_staff.html");
    }
    // 动态设置用户名
    $(".account").each(function (index, accountText) {
        $(accountText).text(staff_account);
    });
    // 动态获取头像信息
    // 创建ajax进行传递数据
    // 1.创建对象
    const xhr = new XMLHttpRequest();
    // 2.初始化 设置类型与URL
    xhr.open("POST", domain + "/staff_inf/index_inf");
    // 3.发送
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); //这行代码很关键，用来把字符串类型的参数序列化成Form Data
    xhr.send(`staffId=${sessionStorage.getItem("staff_id")}`);
    // 后台成功接收到传输的数据
    // 4.事件绑定
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const result = JSON.parse(xhr.responseText);
                // 重新渲染页面数据
                render_index_data(result["staff_inf"]);
            }
        }
    };
    // $("#account").text(staff_account);
}
auth();

function logout() {
    // 退出函数
    // 删除用户信息
    sessionStorage.removeItem("staff_account");
    sessionStorage.removeItem("staff_id");
    // 跳转到登录画面
    location.replace("./login_staff.html");
}

function render_index_data(data) {
    // 获取头像并进行更新
    document.querySelectorAll(".userAvatar").forEach(function (value, index) {
        value.setAttribute("src", "http://" + data["headerFile"]);
    });
}
