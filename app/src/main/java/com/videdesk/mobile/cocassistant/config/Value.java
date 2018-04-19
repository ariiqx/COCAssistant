package com.videdesk.mobile.cocassistant.config;

public class Value {

    // DATABASE DETAILS
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "videCOCHymnal";

    public static final String KEY_FOLDER_AUDIO = "Audio";
    public static final String KEY_FOLDER_DOCUEMENTS = "Documents";
    public static final String KEY_FOLDER_IMAGES = "Images";
    public static final String KEY_FOLDER_PHOTOS = "Photos";
    public static final String KEY_FOLDER_PROFILES = "Profiles";
    public static final String KEY_FOLDER_VIDEO = "Videos";

    public static final String KEY_GENDER_MALE = "male";
    public static final String KEY_GENDER_FEMALE = "female";
    public static final String KEY_USER_READ = "read";
    public static final String KEY_USER_WRITE = "write";
    public static final String KEY_USER_ADMIN = "admin";

    public static final String KEY_STATUS_PENDING = "pending";
    public static final String KEY_STATUS_ACTIVE = "active";
    public static final String KEY_STATUS_EXPIRED = "expired";
    public static final String KEY_STATUS_BLOCKED = "blocked";
    public static final String KEY_STATUS_DELETED = "deleted";

    public static final String KEY_ERH_SMALL = "ɛ";
    public static final String KEY_ERH_BIG = "Ɛ";
    public static final String KEY_ORR_SMALL = "ɔ";
    public static final String KEY_ORR_BIG = "Ɔ";

    public static final String KEY_BIBLE_BOOKS = "bible_books";
    public static final String KEY_BIBLE_CHAPS = "bible_chaps";
    public static final String KEY_BIBLE_VERSES = "bible_verses";

    public static final String KEY_BIBLE_ASV = "asv";
    public static final String KEY_BIBLE_BBE = "bbe";
    public static final String KEY_BIBLE_DEB = "deb";
    public static final String KEY_BIBLE_KJV = "kjv";
    public static final String KEY_BIBLE_WBT = "wbt";
    public static final String KEY_BIBLE_WEB = "web";
    public static final String KEY_BIBLE_YLT = "ylt";

    public static final String KEY_HALF = "half";
    public static final String KEY_GENRE = "genre";
    public static final String KEY_BIBLE = "bible";
    // USERS
    public static final String TABLE_APPZ = "appz";
    public static final String TABLE_PEOPLE = "people";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_THEMES = "themes";
    public static final String TABLE_LIKES = "likes";
    // DIRECTORY
    public static final String TABLE_NATIONS = "nations";
    public static final String TABLE_REGIONS = "regions";
    public static final String TABLE_CHURCHES = "churches";
    // BIBLE
    public static final String TABLE_VERSES = "verses";
    // LESSONS & REFERENCES
    public static final String TABLE_TOPICS = "topics";
    // LESSONS
    public static final String TABLE_LESSONS = "lessons";
    // REFERENCES
    public static final String TABLE_TRACKS = "tracks";
    public static final String TABLE_FACTS = "facts";
    // SERMONS
    public static final String TABLE_SERMONS = "sermons";
    // NOTEBOOKS
    public static final String TABLE_NOTEBOOKS = "notebooks";
    // STUDY GUIDES
    public static final String TABLE_GUIDES = "guides";
    // BIBLE QUIZZES
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String TABLE_LEVELS = "levels";
    public static final String TABLE_QUESTIONS = "questions";

    public static final String COLUMN_USER_NODE = "user_node";
    public static final String COLUMN_PERSON_node = "person_node";
    public static final String COLUMN_LIKE = "like_node";
    public static final String COLUMN_CHURCH_NODE = "church_node";
    public static final String COLUMN_NATION_NODE = "nation_node";
    public static final String COLUMN_REGION_NODE = "region_node";
    public static final String COLUMN_THEME_NODE = "theme_node";
    public static final String COLUMN_ITEM_NODE = "item_node"; //--LIKES
    public static final String COLUMN_BIBLE_NODE = "bible_node";
    public static final String COLUMN_GENRE_NODE = "genre_node";
    public static final String COLUMN_HALF_NODE = "half_node";
    public static final String COLUMN_BOOK_NODE = "book_node";
    public static final String COLUMN_CHAPTER_NODE = "chapter_node";
    public static final String COLUMN_VERSE_NODE = "verse_node";
    public static final String COLUMN_TOPIC_NODE = "topic_node";
    public static final String COLUMN_BIBLE_PAGE = "bible_page";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_NODE = "node";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CAPTION = "caption";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CREATED = "created";
    public static final String COLUMN_UPDATED = "updated";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_BIRTH = "birth";
    public static final String COLUMN_EDUCATION = "education";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_CHAPTERS = "chapters";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_CAREER = "career";
    public static final String COLUMN_DIAL = "dial";


    // LIKE SQL
    public static final String SQL_LIKE = "CREATE TABLE "  + TABLE_LIKES + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NODE + " TEXT," +
            COLUMN_THEME_NODE + " TEXT," +
            COLUMN_ITEM_NODE + " TEXT" +
            ")";

    // USER SQL
    public static final String SQL_USER = "CREATE TABLE "  + TABLE_USERS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NODE + " TEXT" +
            COLUMN_ROLE + " TEXT" +
            COLUMN_NAME + " TEXT" +
            COLUMN_EMAIL + " TEXT" +
            COLUMN_PHONE + " TEXT" +
            COLUMN_PASS + " TEXT" +
            COLUMN_CODE + " TEXT" +
            COLUMN_PHOTO + " TEXT" +
            COLUMN_CREATED + " DATETIME" +
            COLUMN_UPDATED + " DATETIME" +
            COLUMN_STATUS + " TEXT" +
            ")";

    // PERSON SQL
    public static final String SQL_PERSON = "CREATE TABLE "  + TABLE_PEOPLE + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NODE + " TEXT" +
            COLUMN_USER_NODE + " TEXT" +
            COLUMN_NAME + " TEXT" +
            COLUMN_EMAIL + " TEXT" +
            COLUMN_PHONE + " TEXT" +
            COLUMN_PHOTO + " TEXT" +
            COLUMN_NATION_NODE + " TEXT" +
            COLUMN_REGION_NODE + " TEXT" +
            COLUMN_LOCATION + " TEXT" +
            COLUMN_CHURCH_NODE + " TEXT" +
            COLUMN_GENDER + " TEXT" +
            COLUMN_CREATED + " DATETIME" +
            COLUMN_UPDATED + " DATETIME" +
            COLUMN_STATUS + " TEXT" +
            ")";

    // VERSE SQL
    public static final String SQL_VERSE = "CREATE TABLE "  + TABLE_VERSES + "(" +
            Value.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Value.COLUMN_NODE + " TEXT," +
            Value.COLUMN_CODE + " TEXT," +
            Value.COLUMN_BIBLE_NODE + " TEXT," +
            Value.COLUMN_BOOK_NODE + " TEXT," +
            Value.COLUMN_CHAPTER_NODE + " TEXT," +
            Value.COLUMN_TITLE + " TEXT," +
            Value.COLUMN_DETAILS + " TEXT" +
            ")";

    // TOPIC SQL
    public static final String SQL_TOPIC = "CREATE TABLE "  + TABLE_TOPICS + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY," +
            COLUMN_NODE + " TEXT," +
            COLUMN_TITLE + " TEXT" +
            ")";

    public static String[] themeNodes = {
            "1000",
            "2000",
            "3000",
            "4000",
            "5000",
            "6000",
            "7000",
            "8000",
    };

    public static String[] themeTitle = {
            "Directory",
            "Bible",
            "References",
            "Lessons",
            "Sermons",
            "Notebook",
            "Study Guide",
            "Bible Quiz",
    };

    public static String[] themeImages = {
            "img_directory",
            "img_bible",
            "img_reference",
            "img_lessons",
            "img_sermon",
            "img_notebook",
            "img_guide",
            "img_quiz",
    };

    public static String[] themeColors = {
            "colorDirectory",
            "colorBible",
            "colorReference",
            "colorLessons",
            "colorSermons",
            "colorNotebook",
            "colorGuide",
            "colorQuiz"
    };

    public static String[] topics = {
            "Assurance", "Attendance", "Authority", "Backsliding", "Baptism", "Bible, The Authority", "Blood of Jesus",
            "Benevolence", "Children", "Christ", "Christians", "Church, The", "Church Attendance", "Church, Government",
            "Church = Kingdom", "Church Names", "Church Works", "Confession","Contend / Defend / Fight", "Creation","Cross of Christ",
            "Deacons", "Death", "Denominations (Religions of Men)", "Baptist","Catholic", "Christian Science","Church Of God",
            "Episcopalian", "Jehovah Witnesses", "Lutheran", "Masonry","Mennonite", "Methodist", "Mormon",
            "Presbyterian", "Salvation Army", "Seventh Day Adventist", "United Church Of Christ","Devil", "Discipline","Division",
            "Divorce", "Doctrines of Men", "Dying", "Edification","Elders", "Enemies", "Erring Members",
            "Eternity", "Evangelism", "Evolution", "Faith", "Fall From Grace", "False Teachers", "Fellowship",
            "Fight", "Forgiveness", "Fornication/Sex Sins", "Giving", "God, The Father", "God's Plan To Save", "Godhead",
            "Godly Living", "Gospel", "Grace", "Grief", "Growth", "Healing", "Hearing God's Word",
            "Heaven", "Hell", "Holy Spirit", "Home", "Husbands", "Immorality", "Immorality: Abortion",
            "Immorality: Alcohol", "Immorality: Dancing", "Immorality: Dishonesty", "Immorality: Drugs", "Immorality: Gambling",
            "Immorality: Homosexually", "Immorality: Immodesty", "Immorality: Lying", "Immorality: Pornography",
            "Immorality: Television/Movies/Videos", "Immorality: Tobacco", "Instrumental Music", "Jesus Christ, The Son Of God", "Judgment",
            "Kingdom", "Knowledge", "Law of Moses", "Lords's Supper", "Marriage", "Mercy", "Materialism / Money",
            "Miracles", "Music In Church", "Obedience", "Older People", "Paradise", "Pastors", "Plan of Salvation",
            "Poor", "Prayer", "Preachers and Their Work", "Promises of God", "Religions of Men", "Repentance", "Resurrection",
            "Righteousness", "Salvation", "Satan", "Second Coming of Christ", "Sex Sins", "Sin (and Its Consequences)", "Singing",
            "Stewardship", "Study", "Suffering / Sorrow / Grief", "Temptation", " Trinity", "Tongue", "Undenominational Christianity",
            "Unity", "Wives", "Women", "Word of God", "Works", "Worldliness", "Worship",
            "Worship: Giving", "Worship: Lord's Supper", "Worship: Prayer", "Worship: Singing", "Worship: Teaching", "Youth",
    };

    public static String[] books = {
            "1,1,50,Genesis", "1,1,40,Exodus", "1,1,27,Leviticus", "1,1,36,Numbers", "1,1,34,Deuteronomy", "1,2,24,Joshua",
            "1,2,21,Judges", "1,2,4,Ruth", "1,2,31,1 Samuel", "1,2,24,2 Samuel", "1,2,22,1 Kings", "1,2,25,2 Kings",
            "1,2,29,1 Chronicles", "1,2,36,2 Chronicles", "1,2,10,Ezra", "1,2,13,Nehemiah", "1,2,10,Esther", "1,3,42,Job",
            "1,3,150,Psalms", "1,3,31,Proverbs", "1,3,12,Ecclesiastes", "1,3,8,Song of Solomon", "1,4,66,Isaiah", "1,4,52,Jeremiah",
            "1,4,5,Lamentations", "1,4,48,Ezekiel", "1,4,12,Daniel", "1,4,14,Hosea", "1,4,3,Joel", "1,4,9,Amos",
            "1,4,1,Obadiah", "1,4,4,Jonah", "1,4,7,Micah", "1,4,3,Nahum", "1,4,3,Habakkuk", "1,4,3,Zephaniah",
            "1,4,2,Haggai", "1,4,14,Zechariah", "1,4,4,Malachi", "2,5,28,Matthew", "2,5,16,Mark", "2,5,25,Luke",
            "2,5,21,John", "2,6,28,Acts", "2,7,16,Romans", "2,7,16,1 Corinthians", "2,7,13,2 Corinthians", "2,7,6,Galatians",
            "2,7,6,Ephesians", "2,7,4,Philippians", "2,7,4,Colossians", "2,7,5,1 Thessalonians", "2,7,3,2 Thessalonians", "2,7,6,1 Timothy",
            "2,7,4,2 Timothy", "2,7,3,Titus", "2,7,1,Philemon", "2,7,13,Hebrews", "2,7,5,James", "2,7,5,1 Peter",
            "2,7,3,2 Peter", "2,7,5,1 John", "2,7,1,2 John", "2,7,1,3 John", "2,7,1,Jude", "2,8,22,Revelation",
    };

    public static String[] quizzes = {
            "General Knowledge", "Bible Riddles", "True or False", "Bible Quote"
    };
    /*

        String[] columns = {Value.COLUMN_NODE, Value.COLUMN_CODE, Value.COLUMN_BIBLE_NODE};
        Cursor findEntry = db.query("sku_table", columns,
                Value.COLUMN_BIBLE_NODE + "=? AND " + Value.COLUMN_BOOK_NODE + "=? " + Value.COLUMN_CHAPTER_NODE + "=? ",
                new String[] { bible_node, book_node, chap_node },
                null, null, Value.COLUMN_CODE  + " ASC");

     */
}
