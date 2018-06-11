# URL API
## 总则
1. 前后分离， Ajax+Json通信
2. 返回格式
```json
{
    'status': 0,
    'message': '',
    'data': {
    }
}
```
3. 所有时间均为1970至今的毫秒数

## 验证码
1. get '/validate'
2. \<img src='/validate'>

## 图片
1. hash 
2. \<img src='/img/:hash'>

## 注册
1. post '/user'
```
name:
email:
password:
lisence:驾照码
head:file 头像
type: 用户、技师
phone?:
code:验证码
```
2. 激活front`/active?id=:hash`
url: get `/active`
```
id: hash
```
## 登录
1. post '/session'
```
{
    email: 
    password:
    code:验证码
}
```
2. 返回值
```
hash: 用户头像的hash
```

## 信息管理
1. get '/user/:email'
返回值
```
credit: 信用分
points: 积分
name：名字
lisence:驾照码
phone?:
```
2. put '/user'
```
email:
password:
name:
head:file
```
3. delete '/user/:email'

## 技师审核
1. get '/engineer' 获取当前所有等待审核的技师名单
返回值
```
email: []
```
2. put '/engineer' 管理员审核技师
```
email：技师审核通过
```

## 车辆管理
1. get '/type' 获取所有车辆类型
```
{
    type:[] 类型,
}
```
2. get '/brand/:key' 
```
brand: name
```
3. 用户上传车辆信息
post '/car'
```
email:
type:
card:（id）
brand:
img:[] 1-3
message: (2-200)
price: 
```
4. 车辆审核发布
    - get '/checkcar'
    - 返回值
    ```
    img: []
    type: 
    card:
    brand:
    message:
    ```
    - put '/checkcar/:card' 

## 订单管理
1. 生成订单
    - put '/order'
    ```
    email
    card
    timebegin
    timeend
    ```
2. 获取订单
    - get '/user/:id/order'
    返回值
    ```
    id: 
    card:
    timebegin
    timeend
    ```
3. 完成订单
put '/order'
```
id:
```
3. 取消订单
    - delete '/order/:id'

## 评论
1. post '/comment' 后端生成时间
```
email:
card:
content:
```
2. get '/comment/:card'
```
contents: [{
    name
    time
    content
}]
```