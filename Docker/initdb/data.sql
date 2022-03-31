CREATE DATABASE spring;

use spring;

DROP TABLE IF EXISTS cooking;

CREATE TABLE cooking
(
    DishId INT NOT NULL AUTO_INCREMENT,
    Dishname VARCHAR(100),
    Genre VARCHAR(100),
    PRIMARY KEY(DishId)
);

DROP TABLE IF EXISTS ingredients;

CREATE TABLE ingredients
(
    DishId INT,
    Ingredient1 VARCHAR(100),
    Ingredient2 VARCHAR(100),
    Ingredient3 VARCHAR(100),
    Ingredient4 VARCHAR(100)
);

INSERT INTO `cooking` VALUES (1,'ラーメン','中華'),(2,'唐揚げ','japanese'),(3,'ミートソースパスタ','western'),(4,'ペペロンチーノ','western'),(5,'カルボナーラ','western'),(6,'和風パスタ','western'),(7,'にくじゃが','japanese'),(8,'カレーライス','japanese'),(9,'麻婆豆腐','chinese'),(10,'ハッシュドポーク','western'),(11,'ドライカレー','japanese'),(12,'グラタン','western'),(13,'てりやきチキン','japanese'),(14,'そぼろ丼','japanese'),(15,'マグロ丼','japanese'),(16,'キャベツと肉のクリーム煮','western'),(17,'無敵屋！！！','chinese'),(18,'じゃがいもの味噌そぼろ煮','japanese'),(19,'中華丼','chinese'),(20,'お好み焼き','japanese'),(21,'生姜焼き','japanese'),(22,'トマトチーズのチキン煮','western'),(23,'ハンバーグ','western'),(24,'チキンライス','western'),(25,'タンドリーチキン','western'),(26,'肉うどん','japanese'),(27,'トマトとツナのスパゲッティ','western'),(28,'鶏肉の磯辺焼き','japanese'),(29,'親子丼','japanese'),(30,'和風ハンバーグ','japanese'),(31,'豚肉と大根の煮物','japanese'),(32,'カレーうどん','和食'),(33,'麺類','japanese'),(34,'魚','japanese'),(35,'リゾット','western');

INSERT INTO `ingredients` VALUES (1,'麺','チャーシュー','卵','ほうれん草'),(2,'鶏もも肉','','',''),(3,'豚ひき肉','トマト缶','玉ねぎ',''),(4,'ベーコン','','',''),(5,'卵','粉チーズ','ベーコン',''),(6,'ツナ','','',''),(7,'じゃがいも','にんじん','玉ねぎ','豚肉'),(8,'ルー','肉','じゃがいも','にんじん　玉ねぎ'),(9,'豆腐','豚ひき肉','ねぎ',''),(10,'豚肉','玉ねぎ','',''),(11,'豚ひき肉','玉ねぎ','カレー粉',''),(12,'マカロニ','鶏肉','玉ねぎ',''),(13,'鶏もも肉','','',''),(14,'ひき肉','卵','',''),(15,'まぐろ','','',''),(16,'キャベツ','肉','玉ねぎ',''),(17,'','','',''),(18,'じゃがいも','豚ひき肉','',''),(19,'豚肉','白菜','にんじん',''),(20,'豚肉','キャベツ','',''),(21,'豚肉','玉ねぎ','',''),(22,'トマト缶','鶏もも肉','チーズ',''),(23,'豚ひき肉','玉ねぎ','',''),(24,'鶏もも肉','玉ねぎ','',''),(25,'鶏もも肉','カレー粉','ヨーグルト',''),(26,'肉','うどん','ねぎ',''),(27,'トマト缶','ツナ','玉ねぎ',''),(28,'鶏肉','青のり','',''),(29,'鶏肉','玉ねぎ','卵',''),(30,'鶏ひき肉','玉ねぎ','',''),(31,'豚肉','大根','',''),(32,'うどん','カレー粉','にんじん','じゃがいも'),(33,'','','',''),(34,'魚','','',''),(35,'米','チーズ','牛乳','');