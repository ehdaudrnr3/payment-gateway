--
-- 회원
--
INSERT INTO MEMBER
        (MBR_ID, NAME)
VALUES
        ('0000000010', '회원_0010')
      , ('0000000098', '회원_0098')
      , ('0000000101', '회원_0101')
      , ('0000000127', '회원_0127')
      , ('0000000291', '회원_0291')
      , ('0000000299', '회원_0299')
      , ('0000000300', '회원_0300')
      , ('0000000345', '회원_0345')
      , ('0000000352', '회원_0352')
      , ('0000000366', '회원_0366')
      , ('0000000444', '회원_0444')
      , ('0000000483', '회원_0483')
      , ('0000000562', '회원_0562')
      , ('0000000574', '회원_0574')
      , ('0000000602', '회원_0602')
      , ('0000000641', '회원_0641')
      , ('0000000686', '회원_0686')
      , ('0000000754', '회원_0754')
      , ('0000000780', '회원_0780')
      , ('0000000833', '회원_0833')
      , ('0000000849', '회원_0849')
      , ('0000000911', '회원_0911')
      , ('0000000922', '회원_0922')
      , ('0000000999', '회원_0999')
;


--
-- 결제마스터 - 신용카드, 핸드폰, 계좌이체 등
--
INSERT INTO PAYMENT_MST
        (PMT_CODE, PMT_TYPE, PMT_NAME, PART_CNCL_YN)
VALUES
        ( 'P0001', 'PT01', '신용카드-서버01',    'Y' )
      , ( 'P0001', 'PT02', '신용카드-서버02',    'Y' )
      , ( 'P0001', 'PT03', '신용카드-서버03',    'Y' )
      , ( 'P0002',   NULL, '계좌이체',           'Y' )
      , ( 'P0003', 'PT11', '휴대폰-서버11',      'Y' )
      , ( 'P0003', 'PT12', '휴대폰-서버12',      'Y' )
      , ( 'P0004',   NULL, '결제수단04',         'N' )
      , ( 'P0005',   NULL, '결제수단05',         'Y' )
      , ( 'P0006',   NULL, '결제수단06',         'Y' )
      , ( 'P0007',   NULL, '결제수단07',         'Y' )
      , ( 'P0008',   NULL, '결제수단08',         'Y' )
      , ( 'P0009',   NULL, '결제수단09',         'Y' )
      , ( 'P0010',   NULL, '결제수단10',         'N' )
      , ( 'P0011',   NULL, '결제수단11',         'Y' )
      , ( 'P0012',   NULL, '결제수단12',         'Y' )
      , ( 'P0013',   NULL, '결제수단13',         'N' )
      , ( 'P0014',   NULL, '결제수단14',         'Y' )
      , ( 'P0015',   NULL, '결제수단15',         'Y' )
      , ( 'P0016',   NULL, '결제수단16',         'Y' )
      , ( 'P0017',   NULL, '결제수단17',         'N' )
;


--
-- 결제내역
--
