const domain = "http://localhost:8080/OA"; // 声明主机地址
// 工具函数
function clearbox(box) {
    // 清除一个盒子中的所有子节点

    const childs = box.childNodes;
    for (i = childs.length - 1; i >= 0; i--) {
        box.removeChild(childs[i]);
    }
}
// 下面JS函数用于获取url参数
function getQueryVariable(variable) {
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    for (let i = 0; i < vars.length; i++) {
        let pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
        return false;
    }
}
function reder_edit_data(data) {
    // 修改姓名
    const name_input = document.querySelector("input[name='name']");
    name_input.value = data["name"];
    // 修改电话
    const phone_input = document.querySelector("input[name='phone']");
    phone_input.value = data["phone"];
    // 修改年龄
    const age_input = document.querySelector("input[name='age']");
    age_input.value = data["age"];
    // 修改头像
    // $("#pimages").attr(
    //                             "src",
    //                             "http://" + data.image_path
    //                         );
    document.querySelector("#pimages").setAttribute("src", data["headerFile"]);
    // 修改状态
    if (data["gender"] == true) {
        // 去选择已完成
        html = `
    <div class="layui-form-item">
        <label class="layui-form-label"> 性别 </label>
        <div class="layui-input-block">
            <input
                type="radio"
                name="status"
                value="1"
                title="男"
                checked="checked"
            />
            <div
                class="layui-unselect layui-form-radio layui-form-radioed"
            >
                <i
                    class="layui-anim layui-icon layui-anim-scaleSpring"
                    ></i
                >
                <div>男</div>
            </div>
            <input
                type="radio"
                name="status"
                value="0"
                title="女"
            />
            <div
                class="layui-unselect layui-form-radio"
            >
                <i class="layui-anim layui-icon"></i>
                <div>女</div>
            </div>
        </div>
    </div>`;
        // status_box.innerHTML = html;
        $("[name=gender]").each(function (i, item) {
            if ($(item).val() == "1") {
                //更改选中值
                $(item).prop("checked", true);
                //重新渲染
                layui.use("form", function () {
                    form = layui.form();
                    form.render();
                });
            }
        });
    }
}
function init() {
    // 异步请求获取数据
    // 创建ajax进行传递数据
    // 1.创建对象
    const xhr = new XMLHttpRequest();
    // 2.初始化 设置类型与URL
    xhr.open("POST", domain + "/staffInf/get");
    // 3.发送
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); //这行代码很关键，用来把字符串类型的参数序列化成Form Data
    xhr.send(`staffId=${sessionStorage.getItem("staff_id")}`);
    // 将id传送给输入框存储
    document.querySelector("input[name=staff_id]").value =
        sessionStorage.getItem("staff_id");
    // 后台成功接收到传输的数据
    // 4.事件绑定
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const result = JSON.parse(xhr.responseText);
                // 重新渲染页面数据
                reder_edit_data(result["staff_inf"]);
            }
        }
    };
}

window.addEventListener("load", function () {
    // 首先发送异步请求获取信息
    init();
});
