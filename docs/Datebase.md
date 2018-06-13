## User
user
      email          varchar 主键
      password       varchar 非空
      type           int     非空  0 普通用户 1 技师 2 管理员
      status         int     非空  0 记录逻辑存在 1 记录逻辑删除
      isvalidated    int     非空  0用户未验证   1 用户已经验证
      name              varchar         允许空值
      licence           varchar         允许空值
      head              varchar         允许空值
      phone             varchar         允许空值 
      credit            int             允许空值
      points            int             允许空值