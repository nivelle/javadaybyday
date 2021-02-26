/**
  *
  * 用于阅读数据统计
 */
CREATE TABLE nd_bi_data.user_read_behavior
(
    user_name     String,
    book_id       UInt16,
    book_name     String,
    /**章节ID**/
    chapter_id    String,
    /** 24 书籍、26 听书，27大咖**/
    book_type     UInt16,
    /** 阅读时长 **/
    read_times    UInt16,
    /** 书籍底层分类 **/
    category_id   UInt16,
    /** 书籍底层分类名**/
    category_name String,
    /**渠道**/
    p2            String,
    /**机型**/
    p16           String,
    /**用户Ip地址**/
    ip            String,
    /** 版权方ID**/
    copyright_id  UInt16,
    /**企业ID**/
    company_id    String,
    /**1:在企业套餐里 2:不在企业套餐里**/
    in_co_pkg     UInt16,
    /**用户分组ID **/
    user_groupId  UInt16,
    /** 创建时间 **/
    create_time   DateTime
)ENGINE = MergeTree partition by toYYYYMMDD(create_time) order by (create_time);

/**
  用于交易数据统计
 */
CREATE TABLE nd_bi_data.user_transaction_info
(
    user_name         String,
    book_id           UInt16,
    book_name         String,
    /**章节ID**/
    chapter_id        String,
    /** 24 书籍、26 听书，27大咖**/
    book_type         UInt16,
    /** 书籍底层分类 **/
    category_id       UInt16,
    /** 书籍底层分类名**/
    category_name     String,
    /**渠道**/
    p2                String,
    /**客户端机型**/
    client_type       String,
    /** 赠送币金额**/
    coupon_amount     String,
    /** 版权方ID**/
    copyright_id      UInt16,
    /**企业ID**/
    company_id        String,
    /** 交易来源**/
    origin            String,
    /**苹果礼品账户交易金额**/
    apple_gift_amount String,
    /** 苹果账户交易金额 **/
    apple_amount      String,
    /** 退款账户交易金额 **/
    refund_amount     String,
    /**礼品账户交易金额 **/
    main_gift_amount  String,
    /**主账户交易金额**/
    main_amount       String,
    /** 交易总金额**/
    amount            String,
    /** 创建时间 **/
    create_time       DateTime
)ENGINE = MergeTree partition by toYYYYMMDD(create_time) order by (create_time);