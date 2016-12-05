package dq.lelaohui.com.lelaohuipad.fragement.shop.car;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import dq.lelaohui.com.lelaohuipad.bean.FilterSubscribeData;
import dq.lelaohui.com.lelaohuipad.bean.SerSubsctibeData;
import dq.lelaohui.com.lelaohuipad.bean.SubScribeOrderBean;

/**
 * Created by ZTF on 2016/12/1.
 * 客服服务预约工具类
 */

public class SubScribeServerSets {
    private static final String TAG="SubScribeServerSets";
    private SerSubsctibeData subScribeData=null;
    private UiOperators uiOperators;
    private int execNum=0;
    private int  execNumDay;
    private boolean flag;
    private List<FilterSubscribeData> filterSubscribeDataList=new ArrayList<FilterSubscribeData>();
    private Context context;
    private List<SubScribeOrderBean> subScribeOrderBeanList=new ArrayList<SubScribeOrderBean>();//预约相关信息
    private List<SerSubsctibeData> serSubsctibeDataList=new ArrayList<SerSubsctibeData>();//服务员相关信息

    public SubScribeServerSets(Context context){
        this.context=context;
    }
    public UiOperators getUiOperators() {
        return uiOperators;
    }
    public void setUiOperators(UiOperators uiOperators) {
        this.uiOperators = uiOperators;
    }
    /**
     * 添加预约方法
     * @param filterSubscribeData
     * @return
     */

    public boolean addData(FilterSubscribeData filterSubscribeData){
        if(filterSubscribeData!=null){
         execNumDay=filterSubscribeData.getExecNumDay();
            Log.i(TAG,"execNumDay=="+execNumDay);
            filterSubscribeDataList=new ArrayList<FilterSubscribeData>();
            for (int i=0;i<execNumDay;i++){
                filterSubscribeData.setId(execNumDay);
                uiOperators.setSubScribeData(subScribeData,i);
                 flag=filterSubscribeDataList.add(filterSubscribeData);
                if (flag){
                    Log.i(TAG,"filterSubscribeDataList00==="+filterSubscribeDataList.size());
                    Log.i(TAG,"添加数据成功");
                }else{
                    Log.i(TAG,"添加数据失败");
                }
            }
            if (filterSubscribeDataList!=null&&filterSubscribeDataList.size()>0){
                if (uiOperators!=null){
                    uiOperators.setSerPagerName(filterSubscribeDataList.get(execNum).getServiceName());
                    execNum++;
                }
                if (execNum==filterSubscribeDataList.size()){
                    Log.i(TAG,"execNum==="+execNum);
                    if (uiOperators!=null){
                        uiOperators.setButtonTxt("可以提交预约");
                        //设置服务员相关信息
                        uiOperators.setExecNumShowNextView(execNum);
                    }
                }else{
                    if (uiOperators!=null){
                        uiOperators.setButtonTxt("下一次");
                        //设置服务员相关信息
                    }
                }
            }
            /**
             * 初始化点击事件Ui
             */
            initUi();
        }
        return flag;
    }
    /**
     * 修改每次的预约条件数据
     * @param subScribeData
     */
    public void updateData(SerSubsctibeData subScribeData){
        for (int i=0;i<serSubsctibeDataList.size();i++){
            if (serSubsctibeDataList.get(i).getId()==subScribeData.getId()){
                serSubsctibeDataList.set(i,subScribeData);
                Log.i(TAG,"修改成功");
            }
        }
    }
    /**
     * 删除相对应的数据
     * @param filterSubscribeData
     * @return
     */
    public boolean deleteData(FilterSubscribeData filterSubscribeData){
        if (filterSubscribeDataList.contains(filterSubscribeData)){
            boolean flag=filterSubscribeDataList.remove(filterSubscribeData);
            if (flag){
                Log.i(TAG,"删除数据成功");
            }else{
                Log.i(TAG,"删除数据失败");
            }
        }else{
            Log.i(TAG,"要删除的数据不存在");
        }
        return false;
    }
    private void initUi(){
        if(uiOperators==null){
            throw new RuntimeException(getClass().getSimpleName()+":is erro  uiOperator is null");
        }
        if(uiOperators!=null){
            uiOperators.nextButton().setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Snackbar.make(uiOperators.nextButton(), "当前点击上一个", Snackbar.LENGTH_SHORT).show();
                if (execNum==execNumDay){
                    Snackbar.make(uiOperators.nextButton(), "已经是最后一次，是否要提交预约数据？", Snackbar.LENGTH_SHORT).show();
                }else{
                }
                }
            });
            uiOperators.uploadButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(uiOperators.uploadButton(), "下一个或提交", Snackbar.LENGTH_SHORT).show();
                    if (execNum==execNumDay){
                        uiOperators.setButtonTxt("确认提交");
                        serSubsctibeDataList=uiOperators.setSubScribeDataList();
                        if (serSubsctibeDataList!=null){
                            Log.i(TAG,"serSubsctibeDataList.size() is =="+serSubsctibeDataList.size());
                        }
                    }
                }
            });
        }
    }
    public interface UiOperators {
        /**
         * 下一步
         * @return
         */
        View nextButton();

        /**
         * 提交数据
         * @return
         */
        View uploadButton();
        /**
         * 设置提交按钮文字
         * @param txtStr
         */
        void setButtonTxt(String txtStr);
        /**
        *服务内容
         */
        void setSerPagerName(String serPagerName);
        /**
         * 显示第几次
         * @param execNum
         */
        void setExecNumShowNextView(int execNum);
        /**
         * 修改服务员选择相关
         * @param subScribeData
         * @param execNum
         */
        void setSubScribeData(SerSubsctibeData subScribeData,int execNum);

        List<SerSubsctibeData> setSubScribeDataList();
    }

}
