package dq.lelaohui.com.lelaohuipad.job.lowversion;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import dq.lelaohui.com.lelaohuipad.dao.ProCateServiceDaoOperator;

/**
 * Created by ThinkPad on 2016/10/26.
 */

public class StubProvider extends ContentProvider {
    /**
     * Content authority for this provider.
     */
    private static final String AUTHORITY = "";
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final String FOOT_ACTION="foot";
    public static final String FOOT_ALL_ACTION="foot/*";
    public static final String SERVER_ACTION="server";
    public static final String SERVER_ALL_ACTION="server/*";
    public static final int SERVER_FIRST_MENUM=2;
    public static final int SERVER_TWO_MENUM=3;
    static {
        sUriMatcher.addURI(AUTHORITY, FOOT_ACTION, 0);
        sUriMatcher.addURI(AUTHORITY, FOOT_ALL_ACTION, 1);
        sUriMatcher.addURI(AUTHORITY, SERVER_ACTION, SERVER_FIRST_MENUM);
        sUriMatcher.addURI(AUTHORITY, SERVER_ALL_ACTION, SERVER_TWO_MENUM);
    }
    private ProCateServiceDaoOperator proCateServiceDaoOperator;
    @Override
    public boolean onCreate() {
        proCateServiceDaoOperator=ProCateServiceDaoOperator.getInstance();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
       int id= sUriMatcher.match(uri);
        Cursor curor;
        switch (id){
            case  SERVER_FIRST_MENUM:
                if(projection==null||projection.length<2){
                    return null;
                }
                long orgId=Long.parseLong(projection[0]) ;int orgTypeId=Integer.parseInt(projection[1]);
                curor= proCateServiceDaoOperator.queryFirst(orgId,orgTypeId);
                Context ctx = getContext();
                curor.setNotificationUri(ctx.getContentResolver(), uri);
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
