const domain = "http://localhost:8080/OA"; // 声明主机地址
const items = 9;
// 工具函数
function clearbox(box) {
    // 清除一个盒子中的所有子节点

    const childs = box.childNodes;
    for (i = childs.length - 1; i >= 0; i--) {
        box.removeChild(childs[i]);
    }
}
function render_page_num(page, all_pages) {
    // 绘制页码导航
    // page变量从0开始计数
    // all_pages变量为总页码,真正的总数，用30除出来的
    // TODO:这个函数可以进行柯里化

    function click_page_num(element) {
        innerHTML = element.innerHTML;
        const now_page_li = document.querySelector(
            "body > div.x-body > nav > ul > li.current"
        );
        const now_page = parseInt(now_page_li.innerHTML);
        if (innerHTML == "下一页") {
            if (element.classList.contains("forbid")) {
                return;
            } else {
                render_inf(now_page + 1);
            }
        } else if (innerHTML == "上一页") {
            if (element.classList.contains("forbid")) {
                return;
            } else {
                render_inf(now_page - 1);
            }
        } else if (
            !element.classList.contains("symbol") &&
            !element.classList.contains("current")
        ) {
            render_inf(parseInt(element.innerHTML));
            return;
        }
    }
    // 获取盒子
    const box = document.querySelector("body > div.x-body > nav > ul");
    clearbox(box);
    let innerHTML = ``;
    // 增加上一页
    if (page > 0) {
        // 说明有上一页
        innerHTML = innerHTML + `<li>上一页</li>`;
    } else {
        // 说明没有上一页
        innerHTML = innerHTML + `<li class="forbid">上一页</li>`;
    }
    // 如果页码小于等于9
    if (all_pages <= 9) {
        for (i = 0; i < all_pages; i++) {
            if (i == page) {
                // 绘制自己选中的页码
                innerHTML = innerHTML + `<li class="current">${page + 1}</li>`;
                continue;
            }
            innerHTML = innerHTML + `<li>${i + 1}</li>`;
        }
    } else {
        let right_page = 3;
        let left_page = 3;
        let j = page - 3;
        // 当页码较小的情况下
        if (page < 5) {
            j = 0;
            right_page = right_page + (4 - page);
            left_page = page;
        } else {
            if (all_pages - (page + 1) >= 4) {
                // 完全正常两边都有省略号
                innerHTML = innerHTML + `<li>1</li>`;
                innerHTML = innerHTML + `<li class="symbol">...</li>`;
            } else {
                // 从左边补上缺的
                left_page = left_page + (4 - (all_pages - (page + 1)));
                if (page - left_page > 0) {
                    innerHTML = innerHTML + `<li>1</li>`;
                    innerHTML = innerHTML + `<li class="symbol">...</li>`;
                    // left_page = left_page;
                    j = page - left_page;
                }
            }
        }

        // 绘制左边的
        for (i = 0; i < left_page; i++, j++) {
            innerHTML = innerHTML + `<li>${j + 1}</li>`;
        }

        // 绘制自己选中的页码
        innerHTML = innerHTML + `<li class="current">${page + 1}</li>`;

        // 绘制右边
        for (i = 1; i <= right_page; i++) {
            if (page + i < all_pages) {
                innerHTML = innerHTML + `<li>${page + i + 1}</li>`;
            } else {
                break;
            }
        }
        if (all_pages - (page + 1) > 4) {
            innerHTML = innerHTML + `<li class="symbol">...</li>`;
            innerHTML = innerHTML + `<li>${all_pages}</li>`;
        } else if (all_pages - (page + 1) == 4) {
            innerHTML = innerHTML + `<li>${all_pages}</li>`;
        }
    }

    // 增加下一页
    if (all_pages - 1 > page) {
        // 说明有下一页
        innerHTML = innerHTML + `<li>下一页</li>`;
    } else {
        // 说明没有下一页
        innerHTML = innerHTML + `<li class="forbid">下一页</li>`;
    }
    box.innerHTML = innerHTML;
    // 下面给每一个li添加点击事件
    box.childNodes.forEach((element) => {
        element.addEventListener("click", function () {
            click_page_num(element);
        });
    });
}
function reder_date(data) {
    // 渲染数据到页面上
    // 获取盒子并清空
    const box = document.querySelector("body > div.x-body > table > tbody");

    clearbox(box);
    let result = ``;
    // 遍历数据，逐条渲染
    data.forEach(function (value, index) {
        let template = `
    <tr>
        <td>
            <input type="checkbox" value="${value["scheduleId"]}" name="schedule_id" />
        </td>
        <td>${value["title"]}</td>
        <td>${value["date"]}</td>
        <td>${value["content"]}</td>
        <td class="td-manage">
            <a
                title="编辑"
                href="javascript:;"
                onclick="schedule_edit('编辑','schedule_edit.html','${value["scheduleId"]}','1000','600')"
                class="ml-5"
                style="text-decoration: none"
            >
                <i class="layui-icon">&#xe642;</i>
            </a>
            <a
                title="删除"
                href="javascript:;"
                onclick="schedule_del(this,'${value["scheduleId"]}')"
                style="text-decoration: none"
            >
                <i class="layui-icon">&#xe640;</i>
            </a>
        </td>
    </tr>`;
        result = result + template;
    });
    // 将结果添加到页面
    box.innerHTML = result;
}
function render_inf(page) {
    // 获取职员id
    // 创建ajax进行传递数据
    // 1.创建对象
    const xhr = new XMLHttpRequest();
    // 2.初始化 设置类型与URL
    xhr.open("POST", domain + "/schedule/getList");
    // 3.发送
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); //这行代码很关键，用来把字符串类型的参数序列化成Form Data
    xhr.send(
        `page=${page}&items=${items}&staffId=${sessionStorage.getItem(
            "staff_id"
        )}`
    );
    // 后台成功接收到传输的数据
    // 4.事件绑定
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300) {
                const result = JSON.parse(xhr.responseText);
                // 重新渲染页面数据
                console.log("后端给前端的时间数据", result);
                reder_date(result["schedule_inf"]);
                render_page_num(page - 1, result["pages"]);
            }
        }
    };
}
function reder_num() {
    // 获取数据数量并渲染到页面上
    // 获取职员id
    // 创建ajax进行传递数据
    // 1.创建对象
    const xhr = new XMLHttpRequest();
    // 2.初始化 设置类型与URL
    xhr.open("POST", domain + "/schedule/num");
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
                if (result["code"] == 200) {
                    // 获取盒子
                    const num_box = document.querySelector(
                        "body>div.x-body>xblock>span>span"
                    );
                    num_box.innerHTML = result["schedule_inf"];
                }
            }
        }
    };
}
function init() {
    render_inf(1);
    reder_num();
}

window.addEventListener("load", function () {
    // 首先发送异步请求获取第一页信息
    init();
});
