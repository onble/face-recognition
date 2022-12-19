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
    // 修改标题
    const title_input = document.querySelector("input[name='title']");
    title_input.value = data["title"];
    // 修改内容
    const content_input = document.querySelector("textarea[name='content']");
    content_input.value = data["content"];
    // 修改状态
    if (data["status"] == true) {
        // 去选择已完成
        const over_input = document.querySelector(
            "input[name='status'][value='1']"
        );
        const not_input = document.querySelector(
            "input[name='status'][value='0']"
        );
        const status_box = document.querySelector("#status_box");
        // clearbox(status_box);
        html = `
        <label class="layui-form-label">
                                    <span class="x-red">*</span>状态
                                </label>
                                <div class="layui-input-block">
                                    <input
                                        type="radio"
                                        name="status"
                                        value="1"
                                        title="已完成"
                                        checked="checked"
                                    />
                                    <div
                                        class="layui-unselect layui-form-radio layui-form-radioed"
                                    >
                                        <i
                                            class="layui-anim layui-icon layui-anim-scaleSpring"
                                            ></i
                                        >
                                        <div>已完成</div>
                                    </div>
                                    <input
                                        type="radio"
                                        name="status"
                                        value="0"
                                        title="未完成"
                                        
                                    />
                                    <div
                                        class="layui-unselect layui-form-radio"
                                    >
                                        <i class="layui-anim layui-icon"></i>
                                        <div>未完成</div>
                                    </div>
                                </div>
        `;
        // status_box.innerHTML = html;
        $("[name=status]").each(function (i, item) {
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
    xhr.open("POST", domain + "/todo/get");
    // 3.发送
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); //这行代码很关键，用来把字符串类型的参数序列化成Form Data
    xhr.send(`id=${getQueryVariable("id")}`);
    // 将id传送给输入框存储
    document.querySelector("input[name=todo_id]").value =
        getQueryVariable("id");
    // 后台成功接收到传输的数据
    // 4.事件绑定
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const result = JSON.parse(xhr.responseText);
                // 重新渲染页面数据
                reder_edit_data(result["todo_inf"]);
            }
        }
    };
}

window.addEventListener("load", function () {
    // 首先发送异步请求获取第一页信息
    init();
});
