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
    const name_input = document.querySelector("input[name='name']");
    name_input.value = data["name"];
    // 修改内容
    const homePage_input = document.querySelector("textarea[name='homePage']");
    homePage_input.value = data["homePage"];
	}
}
function init() {
    // 异步请求获取数据
    // 创建ajax进行传递数据
    // 1.创建对象
    const xhr = new XMLHttpRequest();
    // 2.初始化 设置类型与URL
    xhr.open("POST", domain + "/Todo/get");
    // 3.发送
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); //这行代码很关键，用来把字符串类型的参数序列化成Form Data
    xhr.send(`id=${getQueryVariable("id")}`);
    // 将id传送给输入框存储
    document.querySelector("input[name=id]").value =
        getQueryVariable("id");
    // 后台成功接收到传输的数据
    // 4.事件绑定
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const result = JSON.parse(xhr.responseText);
                // 重新渲染页面数据
                reder_edit_data(result["Todo_inf"]);
            }
        }
    };
}

window.addEventListener("load", function () {
    // 首先发送异步请求获取第一页信息
    init();
});
