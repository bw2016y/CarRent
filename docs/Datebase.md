# Table
## user
     1  email       varchar     主键
     2  password        varchar         非空
     3  type        int     非空  0 普通用户 1 技师 2 管理员
     4  status         int      非空  0 记录逻辑存在 1 记录逻辑删除
     5  isvalidated    int      非空  0用户未验证   1 用户已经验证
     6  name        varchar         允许空值
     7  licence     varchar     允许空值
     8  head        varchar         允许空值
     9  phone       varchar     允许空值 
     10 credit      int     允许空值
     11 points      int     允许空值