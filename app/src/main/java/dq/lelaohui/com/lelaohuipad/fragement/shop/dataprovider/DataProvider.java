package dq.lelaohui.com.lelaohuipad.fragement.shop.dataprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class DataProvider extends ContentProvider {
    public static final String AUTHORITY = "lelahui.foot";
    public static final String PATH_SINGLE = "foot/cate";
    public static final String PATH_MULTIPLE = "foot/info";
    private static final String CONTENT_URI_FOOT_INFO_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
    public static final String CONTENT_URI_FOOT_CATE_STRING = "content://" + AUTHORITY + "/" + PATH_SINGLE;
    public static final Uri CONTENT_FOOT_CATE_URI = Uri.parse(CONTENT_URI_FOOT_CATE_STRING);
    public static final Uri CONTENT_FOOT_INFO_URI = Uri.parse(CONTENT_URI_FOOT_INFO_STRING);
    public static final int MULTIPLE_PEOPLE = 1;
    public static final int SINGLE_PEOPLE = 2;
    public static final UriMatcher uriMatcher;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH_SINGLE, SINGLE_PEOPLE );
        uriMatcher.addURI(AUTHORITY, PATH_MULTIPLE , MULTIPLE_PEOPLE );
    }
    public DataProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        switch(uriMatcher.match(uri)){
            case MULTIPLE_PEOPLE:
                //多条数据的处理
                return PATH_SINGLE;
            case SINGLE_PEOPLE:
                //单条数据的处理
                return CONTENT_URI_FOOT_CATE_STRING;
            default:
                throw new IllegalArgumentException("不支持的URI:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
