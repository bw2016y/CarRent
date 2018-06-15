# 前端请求URL文档
------
## 总则
1. 项目采取前后分离的设计思路,使用`ajax`进行前后端的通信
2. 所有的返回数据全为JSON格式,有如下规定. 假设返回的数据名为data
    - `data.code` 标识请求是否成功, 0为正常, 1为失败
    - `data.message` 为请求的错误消息, 只在`data.code===1`时有效
    - `data.data` 为请求返回的有效数据, 只在`data.code===0`时有效
3. 项目所有的时间均为1970年以来的毫秒数
4. 项目所有的文件均为`哈希值加扩展名`进行url编码后的结果
------
## 验证码
1. 请求的URL为`GET '/validate'`
2. 返回值为二进制的图片流, 可以直接使用`img`标签显示
---
## 注册与激活
1. 注册
    - 请求的URL为`POST '/user'`
    - 请求所包含的参数为
        - `name`: 用户名, 长度为2-10
        - `email`:用户`Email`
        - `password`: 用户密码, 长度为6-20
        - `license`: 驾照码
        - `file`: 用户头像
        - `type`: 用户类型, 普通用户0或者技师1
        - `phone`: 用户电话
        - `code`: 验证码
    - 返回值
        - 当`code`为1时标识注册失败
        - 当`code`为0时标识注册成功
2. 激活用户
    - 服务器发送的激活链接格式为`/active?id=:hash`
    - 请求URL为`GET '/active'`
    - 请求包含的参数为
        - `id`: 服务器发送邮件中的序列码,即url中的`id`
    - 返回值
        - 当`code`为0时标识激活成功
        - 当`code`为1时标识激活失败
-------
## 登陆与登出
1. 登陆
    - 请求URL `POST '/session'`
    - 请求参数
        - `email`: 用户邮箱
        - `password`: 用户密码
        - `code`: 验证码
    - 返回值
        - `name`: 用户头像
        - `file`: 用户头像文件名
        - `type`: 用户类型
2. 登出
    - 请求URL `DELETE '/session'`
    - 无请求参数
    - 无返回值
-----
## 信息管理
1. 获取用户信息
    - 请求URL` GET '/user/:email`
    - 无请求参数
    - 返回值
        - `email`: 用户邮箱
        - `name`: 用户名
        - `credit`: 用户信用
        - `points`: 用户积分
        - `license`: 用户驾照条码
        - `type`: 用户类型, 普通用户0或者技师1或者管理员2
        - `file`: 用户头像名
        - `phone`: 用户手机号
2. 更改用户信息
    - 请求URL`PUT '/user'`
    - 请求参数
        - `email`: 用户邮箱
        - `password`: 用户密码
        - `name`: 用户名
        - `file`: 用户头像
    - 无实际返回值
3. 删除用户
    - 请求URL`DELETE '/user/:email`
    - 无请求参数
    - 无实际返回值
4. 忘记密码与重置
    - 请求发送重置邮件的URL` GET '/password'`
    - 请求参数为
        - `email`: 用户的`email`
    - 邮件格式为`/reset?id=:hash`
    - 重置密码URL `PUT '/password'`
    - 请求参数为
        - `hash`: 用户邮件中的序列码
        - `password`: 用户新密码, 长度6-20
    - 无实际返回值
----
## 技师审核
1. 获取待审核技师列表
    - 请求URL`GET '/engineer'`
    - 无请求参数
    - 返回值
        - `email`: 技师的`email`
        - `name`: 技师名
        - `hash`: 头像名
        - `phone`: 技师电话
2. 管理员审核技师
    - 请求URL `put '/engineer'`
    - 请求参数
        - `email`: 通过审核技师的`email`
    - 无实际返回值
------
## 车辆管理
1. 获取车辆类型
    - 请求URL`GET '/type'`
    - 无请求参数
    - 返回值
        - `type[]`: 车辆类型数组
2. 添加车辆类型
    - 请求URL `POST '/type'`
    - 请求参数
        - `number`: 座位数
    - 无实际返回值
3. 获取车辆品牌
    - 请求URL`GET '/brand'`
    - 无请求参数
    - 返回值
        - `brands`: map列表, 每个map中包含两个属性
            - `name`: 品牌名称
            - `hash`: 品牌logo图片名
4. 添加车辆品牌
    - 请求URL`POST '/brand'`
    - 请求参数
        - `name`: 品牌名称
        - `file`: 品牌logo
    - 无实际返回值
5. 用户上传车辆信息
    - 请求URL`POST '/car'`
    - 请求参数
        - `email`: 用户email
        - `type`: 车辆类型
        - `card`: 车牌号
        - `brand`: 车品牌, 车品牌与车型连接发送
        - `file`: 车图像文件列表
        - `message`: 汽车简介
        - `price`: 汽车价格
    - 无实际返回值
6. 获取待审核车辆
    - 请求URL `GET '/checkcar'`
    - 无请求参数
    - 返回值
        - `img`: 车辆的图片列表
        - `type`: 车辆类型
        - `card`: 车牌
        - `brand`: 车品牌
        - `message`: 车辆评价
7. 技师发布车辆
    - 请求URL `PUT '/checkcar'`
    - 请求参数
        - `card`: 车牌号
    - 无实际返回值
8. 查找车辆
    - 请求URL`GET '/car'`
    - 请求参数
        - `page`: 第几页, 从0开始
        - `length`: 每页多少记录
        - `type`: 车辆类型
        - `brand`: 车品牌
    - 返回值
        - `cars`: 车辆列表, 包含以下属性
            - `img[]`: 车辆图片列表
            - `type`: 车辆类型
            - `brand`: 车辆品牌
            - `message`: 车辆简介
            - `price`: 汽车价格
-------
## 订单管理
1. 生成订单
    - 请求URL`PUT '/order'`
    - 请求参数
        - `email`: 生成订单的账号
        - `card`: 车牌号
        - `timebegin`: 开始时间
        - `timeend`: 结束时间
    - 无实际返回值
2. 获取订单
    - 请求URL`GET '/user/:id/order`
    - 无请求参数
    - 返回值
        - `id`: 订单id
        - `card`: 车牌号
        - `timebegin`: 开始时间
        - `timeend`: 结束时间
3. 完成订单
    - 请求URL`PUT '/order'`
    - 请求参数
        - `id`: 订单号
    - 无实际返回值
4. 取消订单
    - 请求URL`DELETE '/order/:id'`
    - 无请求参数
    - 无实际返回值
----
## 评论
1. 生成评论
    - 请求URL`POST '/comment'`
    - 请求参数
        - `content`: 评论内容
        - `orderId`: 订单id
    - 无实际返回值
2. 获取评论
    - 请求URL`GET '/comment/:card'`
    - 无请求参数
    - 返回值
        - `contents`: 评论列表, 有如下几个属性
            - `name`: 评论人
            - `time`: 评论时间
            - `content`: 评论内容
