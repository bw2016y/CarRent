# Table
## user
     1  email       varchar     主键
     2  password        varchar         非空
     3  type        int     非空  0 普通用户 1 技师 2 管理员
     4  status         int      非空  0 记录逻辑存在 1 记录逻辑删除
     5  isvalidated    int      非空  0用户禁封（不可用）   1 用户可用
     6  name        varchar         允许空值
     7  licence     varchar     允许空值
     8  head        varchar         允许空值
     9  phone       varchar     允许空值 
     10 credit      int     允许空值
     11 points      int     允许空值
     12 isauthorized int                    1技师验证成功 0技师未验证
     13 balance   bigint  允许空值          账户余额
## car
    1 card      varchar     主键      非空
    2 brand     varchar     非空
    3 message       varchar 
    4 price         int      非空
    5 ischecked     int      非空 0未检查 1已检查
    6 available     int     非空用  1不可用 0 可
    7 status        int     非空 0逻辑存在 1逻辑删除
    8 type          int     非空  表示汽车的可乘坐人数
    9 email       varchar     非空      车主          
    10 city       varchar     非空      城市        
## ordertable
    1  orderid      int     主键    NOT  NULL 自增
    2 email         varchar 非空
    3 card          varchar 非空
    4 timebegin     bigint         开始时间
    5 timeende      bigint        实际结束时间    
    6 timeendr      bigint        评论时间
    7 comment       varchar
    8 status        int     非空 0逻辑存在 1逻辑删除
    9 type       int       非空       0 订单进行中（开始计时-结束计时） 1 订单未支付（结束计时-付款） 2 订单未完成 （付款-技师检查） 3 订单已完成 （技师检查-用户评论） 
    10 money      bigint                  订单数额     

##  car_brand
    1 brand         varchar 主键  非空
    2 img           varchar 
##  car_img
    1 imgid         int     主键 非空 自增
    2 card          varchar     
    3 img           varchar 
## car_type
    1 type          int     主键 非空
    2 description   varchar 
## citytable
    1 city          varchar     非空      主键  
    2 site          varchar     