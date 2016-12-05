package dq.lelaohui.com.lelaohuipad.bean;

import java.util.List;

/**
 * Created by ZTF on 2016/11/30.
 */

public class ServerPersonCate {

    /**
     * code : 0
     * msg : 成功
     * data : [{"userId":"101000100002000006","userCode":"2000006","userName":"1103入院测试老人1","telephone":"15010797278","sfz":"110110195212125651","sex":"1","experience":"2.5"},{"userId":"101000100002000007","userCode":"2000007","userName":"1103入院测试老人2","telephone":"15010797277","sfz":"110110195212125652","sex":"1","experience":"2.5"},{"userId":"101000100002000008","userCode":"2000008","userName":"入院测试3","telephone":"15010787276","sfz":"11011019565252","sex":"1","experience":"2.5"},{"userId":"101000000002000036","userCode":"2000036","userName":"小红帽","telephone":"12345678934","sfz":"130423198808151059","sex":"1","experience":"2.5"},{"userId":"101000000002000037","userCode":"2000037","userName":"小书包","telephone":"18923822803","sfz":"130423198808139820","sex":"2","experience":"2.5"},{"userId":"101000000002000042","userCode":"2000042","userName":"老人A","telephone":"18923822801","sfz":"123456789012365671","sex":"1","experience":"2.5"},{"userId":"101000000002000043","userCode":"2000043","userName":"老人B","telephone":"18923822805","sfz":"123456789012365672","sex":"1","experience":"2.5"},{"userId":"101000000002000044","userCode":"2000044","userName":"田七","telephone":"18923822809","sfz":"123456789012365675","sex":"1","experience":"2.5"}]
     */

    private int code;
    private String msg;
    private List<ServerPersonData> data;

    @Override
    public String toString() {
        return "ServerPersonCate{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ServerPersonData> getData() {
        return data;
    }

    public void setData(List<ServerPersonData> data) {
        this.data = data;
    }

}
