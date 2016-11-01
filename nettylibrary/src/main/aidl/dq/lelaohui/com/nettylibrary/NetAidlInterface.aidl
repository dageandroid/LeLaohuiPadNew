// NetAidlInterface.aidl
package dq.lelaohui.com.nettylibrary;

// Declare any non-default types here with import statements

interface NetAidlInterface {
    void init(String id,String port);
    void connect();
   void regeist(String message);
   void unregeist(String message);
}
