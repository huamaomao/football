package com.vxfc.shenxin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.vxfc.shenxin.R;
import com.vxfc.shenxin.domian.AgainstVo;
import com.vxfc.shenxin.domian.Formation;
import com.vxfc.shenxin.util.Team;
import com.vxfc.shenxin.util.Util;

import java.util.*;

/**
 * Created by Hua on 2014/8/13.
 */
public class FormationView extends FrameLayout{

   public interface  OnItemClickListener{
        /****
         *
         * @param item 球号
         * @param flag  白队（true）  黄队（false）
         */
       public void   onItemClick( AgainstVo.Item item, boolean flag);

       /***
        *  换球队阵容
        * @param flag
        */
       public void   onTeamFormation(boolean flag);
    }
    public ImageView teamIcon;
    private OnItemClickListener onItemClickListener;

    public int resIcon= Team.ShenXin.icon_t;
    public static final String TAG="test";
    private static HashSet team;
    /***  11名球员  ****/
    private Map<Integer,AgainstVo.Item> teamMap=new HashMap<Integer, AgainstVo.Item>(11);
    /***** 白红阵型 ****/
    private Formation formation;
    private boolean flag=true;
    private int mWidth=160;
    /**球衣的宽高****/
    private int mHeight=160;
    /**  控件的宽高****/
    private int width;
    private int height;
    private float textSize=14f;

    private LayoutInflater inflater;
    /***
     * 初始化控件和数据
     */
    public void initData(){
        if (Util.isNull(formation)) return;
        if(formation.getLength()<3)return;
        this.width=getMeasuredWidth();
        this.height=getMeasuredHeight();
        teamIcon.setImageResource(resIcon);
        teamIcon.setY(0);
        teamIcon.setX(0);
        /******1*********/
        changeFormation(0, width / 2 - mWidth / 2, 0,1);
        /******************白队****************/
        int x1=0,n=0;
       int avgY=height/(formation.getLength());
       switch (formation.getaValue()){
            case 5:  /******  532 ***541 ********/
                n=width/6;
                changeFormation(1, x1, avgY, 2);
                changeFormation(2,x1+n,avgY,6);
                changeFormation(3,x1+n*2,avgY,5);
                changeFormation(4,x1+n*3,avgY,4);
                changeFormation(5,x1+n*4,avgY,3);
                if (formation.getbValue()==3){
                    n=width/4;
                    x1=n-mWidth/2;
                    changeFormation(6,x1,avgY*2,7);
                    changeFormation(7,x1+n,avgY*2,8);
                    changeFormation(8,x1+n*2,avgY*2,11);
                    n=width/3;
                    x1=n-mWidth/2;
                    changeFormation(9,x1,avgY*3,10);
                    changeFormation(10,x1+n,avgY*3,9);
                }else{
                    n=width/5;
                    x1=n-mWidth/2;
                    changeFormation(6,x1,avgY*2,7);
                    changeFormation(7,x1+n,avgY*2,8);
                    changeFormation(8,x1+n*2,avgY*2,10);
                    changeFormation(9,x1+n*3,avgY*2,11);
                    n=width/2;
                    x1=n-mWidth/2;
                    changeFormation(10,x1,avgY*3,9);
                }
                break;
            case 4:
                n=width/5;
                x1=n-mWidth/2;
                changeFormation(1,x1,avgY,2);
                changeFormation(2,x1+n,avgY,5);
                changeFormation(3,x1+n*2,avgY,6);
                changeFormation(4,x1+n*3,avgY,3);
                switch (formation.getbValue()){
                    case 5:
                        n=width/6;
                        x1=n-mWidth/2;
                        /*********451*********/
                        changeFormation(5,x1,avgY*2,7);
                        changeFormation(6,x1+n,avgY*2,4);
                        changeFormation(7,x1+n*2,avgY*2,10);
                        changeFormation(8,x1+n*3,avgY*2,8);
                        changeFormation(9,x1+n*4,avgY*2,11);
                        n=width/2;
                        x1=n-mWidth/2;
                        changeFormation(10,x1,avgY*3,9);
                        break;
                    case 4:
                        /*********442****4411*****/
                        n=width/5;
                        x1=n-mWidth/2;
                        changeFormation(5,x1,avgY*2,7);
                        changeFormation(6,x1+n,avgY*2,4);
                        changeFormation(7,x1+n*2,avgY*2,8);
                        changeFormation(8,x1+n*3,avgY*2,11);
                        if (formation.getcValue()==2){
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(9,x1,avgY*3,10);
                            changeFormation(10,x1+n,avgY*3,9);
                        }else {
                            n=width/2;
                            x1=n-mWidth/2;
                            changeFormation(9,x1,avgY*3,10);
                            changeFormation(10,x1,avgY*4,9);
                        }
                        break;
                    case 3:
                        /****433*****4321*******4312********/
                        n=width/4;
                        x1=n-mWidth/2;
                        if(formation.getcValue()==3) {
                            changeFormation(5,x1,avgY*2,7);
                            changeFormation(6,x1+n,avgY*2,4);
                            changeFormation(7,x1+n*2,avgY*2,8);

                            changeFormation(8,x1,avgY*3,10);
                            changeFormation(9,x1+n,avgY*3,9);
                            changeFormation(10,x1+n*2,avgY*3,11);
                        }else if(formation.getcValue()==1){
                            n=width/4;
                            x1=n-mWidth/2;
                            changeFormation(5,x1,avgY*2,7);
                            changeFormation(6,x1+n,avgY*2,4);
                            changeFormation(7,x1+n*2,avgY*2,11);

                            n=width/2;
                            x1=n-mWidth/2;
                            changeFormation(8,x1,avgY*3,8);
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(9,x1,avgY*4,9);
                            changeFormation(10,x1+n,avgY*4,10);
                        }else {
                            n=width/4;
                            x1=n-mWidth/2;
                            changeFormation(5,x1,avgY*2,8);
                            changeFormation(6,x1+n,avgY*2,4);
                            changeFormation(7,x1+n*2,avgY*2,7);
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(8,x1,avgY*3,10);
                            changeFormation(9,x1+n,avgY*3,11);
                            n=width/2;
                            x1=n-mWidth/2;
                            changeFormation(10,x1,avgY*4,9);
                        }
                        break;
                    case 2:
                        /*****4231*********4222*******424**************/
                        if(formation.getcValue()==3) {
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(5,x1,avgY*2,8);
                            changeFormation(6,x1+n,avgY*2,4);
                            n=width/4;
                            x1=n-mWidth/2;
                            changeFormation(7,x1,avgY*3,7);
                            changeFormation(8,x1+n,avgY*3,10);
                            changeFormation(9,x1+n*2,avgY*3,11);
                            n=width/2;
                            x1=n-mWidth/2;
                            changeFormation(10,x1,avgY*4,9);
                        }else if(formation.getcValue()==4){
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(5,x1,avgY*2,4);
                            changeFormation(6,x1+n,avgY*2,8);
                            n=width/5;
                            x1=n-mWidth/2;
                            changeFormation(7,x1,avgY*3,7);
                            changeFormation(8,x1+n,avgY*3,9);
                            changeFormation(9,x1+n*2,avgY*3,10);
                            changeFormation(10,x1+n*3,avgY*3,11);
                        }else {
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(5,x1,avgY*2,4);
                            changeFormation(6,x1+n,avgY*2,8);

                            changeFormation(7,x1,avgY*3,7);
                            changeFormation(8,x1+n,avgY*3,11);

                            changeFormation(9,x1,avgY*4,10);
                            changeFormation(10,x1+n,avgY*4,9);
                        }
                        break;
                    case 1: /*********4141**********41212************4132*************/
                        n=width/2;
                        x1=n-mWidth/2;
                        changeFormation(5,x1,avgY*2,4);
                        if(formation.geteValue()==2) {
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(6,x1,avgY*3,7);
                            changeFormation(7,x1+n,avgY*3,11);
                            n=width/2;
                            x1=n-mWidth/2;
                            changeFormation(8,x1,avgY*4,8);
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(9,x1,avgY*5,10);
                            changeFormation(10,x1+n,avgY*5,9);
                        }else if(formation.geteValue()==3){
                            n=width/4;
                            x1=n-mWidth/2;
                            changeFormation(6,x1,avgY*3,7);
                            changeFormation(7,x1+n,avgY*3,8);
                            changeFormation(8,x1+n*2,avgY*3,11);
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(9,x1,avgY*4,9);
                            changeFormation(10,x1+n,avgY*4,10);
                        } else{
                            n=width/5;
                            x1=n-mWidth/2;
                            changeFormation(6,x1,avgY*3,7);
                            changeFormation(7,x1+n,avgY*3,8);
                            changeFormation(8,x1+n*2,avgY*3,11);
                            changeFormation(9,x1+n*3,avgY*3,9);
                            n=width/2;
                            x1=n-mWidth/2;
                            changeFormation(10,x1,avgY*4,9);
                        }
                        break;
                }
                break;
            case 3 /*****352****343******31312*********3511**********3421*********3412*******3142******/:
                n=width/4;
                x1=n-mWidth/2;
                if (formation.getcValue()==4){
                    // 3142
                    changeFormation(1,x1,avgY,5);
                    changeFormation(2,x1+n,avgY,4);
                    changeFormation(3,x1+n*2,avgY,3);
                    n=width/2;
                    x1=n-mWidth/2;
                    changeFormation(4,x1,avgY*2,8);
                    n=width/5;
                    x1=n-mWidth/2;
                    changeFormation(5,x1,avgY*3,2);
                    changeFormation(6,x1+n,avgY*3,7);
                    changeFormation(7,x1+n*2,avgY*3,11);
                    changeFormation(8,x1+n*3,avgY*3,3);
                    n=width/3;
                    x1=n-mWidth/2;
                    changeFormation(9,x1,avgY*4,10);
                    changeFormation(10,x1+n,avgY*4,9);
                    break;
                }
                changeFormation(1,x1,avgY,6);
                changeFormation(2,x1+n,avgY,5);
                changeFormation(3,x1+n*2,avgY,4);
                switch (formation.getbValue()){
                    case 5: /****271183***10 9*10* 9   **/
                        n=width/6;
                        x1=n-mWidth/2;
                        changeFormation(4,x1,avgY*2,2);
                        changeFormation(5,x1+n,avgY*2,7);
                        changeFormation(6,x1+n*2,avgY*2,11);
                        changeFormation(7,x1+n*3,avgY*2,8);
                        changeFormation(8,x1+n*4,avgY*2,3);
                        if (formation.getcValue()==2){
                            n=width/3;
                            x1=n-mWidth/2;
                            changeFormation(9,x1,avgY*3,10);
                            changeFormation(10,x1+n,avgY*3,9);
                        }else {
                            n=width/2;
                            x1=n-mWidth/2;
                            changeFormation(9,x1,avgY*3,10);
                            changeFormation(10,x1,avgY*4,9);
                        }
                        break;
                    case 4:/********343**2783 10911*****3421*********3412************31312*******/
                        n=width/5;
                        x1=n-mWidth/2;
                        changeFormation(4,x1,avgY*2,2);
                        changeFormation(5,x1+n,avgY*2,7);
                        changeFormation(6,x1+n*2,avgY*2,8);
                        changeFormation(7,x1+n*3,avgY*2,3);
                        switch (formation.getcValue()) {
                            case 3:
                                n=width/4;
                                x1=n-mWidth/2;
                                changeFormation(8,x1,avgY*3,10);
                                changeFormation(9,x1+n,avgY*3,9);
                                changeFormation(10,x1+n*2,avgY*3,11);
                                break;
                            case 2:
                                n=width/3;
                                x1=n-mWidth/2;
                                changeFormation(8,x1,avgY*3,10);
                                changeFormation(9,x1+n,avgY*3,9);
                                n=width/2;
                                x1=n-mWidth/2;
                                changeFormation(10,x1,avgY*4,11);
                                break;
                            case 1:
                                n=width/2;
                                x1=n-mWidth/2;
                                changeFormation(8,x1,avgY*3,9);
                                n=width/3;
                                x1=n-mWidth/2;
                                changeFormation(9,x1,avgY*4,10);
                                changeFormation(10,x1+n,avgY*4,11);
                                break;
                        }
                        break;
                    case 1:/******************31312*******7 283 9 11******************/
                        n=width/2;
                        x1=n-mWidth/2;
                        changeFormation(4,x1,avgY*2,7);
                        n=width/4;
                        x1=n-mWidth/2;
                        changeFormation(5,x1,avgY*3,2);
                        changeFormation(6,x1+n*1,avgY*3,8);
                        changeFormation(7,x1+n*2,avgY*3,3);
                        n=width/2;
                        x1=n-mWidth/2;
                        changeFormation(8,x1,avgY*4,9);
                        n=width/3;
                        x1=n-mWidth/2;
                        changeFormation(9,x1,avgY*5,10);
                        changeFormation(10,x1+n,avgY*5,11);
                        break;
                }
                break;
        }
    }

    private void init(){
        inflater=LayoutInflater.from(getContext());
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(mWidth,mHeight);
        for (int i=0;i<11;i++){
            createView(params);
        }
        teamIcon=new ImageView(getContext());
        ViewGroup.LayoutParams p=new ViewGroup.LayoutParams(300,300);
        teamIcon.setLayoutParams(p);
        addView(teamIcon);
        teamIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    flag=!flag;
                    onItemClickListener.onTeamFormation(flag);
                }
            }
        });


    }
    /****
     * 改变阵容
     * @param index
     * @param x
     * @param y
     * @param key
     */
    private void  changeFormation(int index,float x,float y,int key){
        View view=(View)getChildAt(index);
        view.setX(x);
        view.setY(y);
        AgainstVo.Item item=teamMap.get(key);
        TextView player=(TextView)view.findViewById(R.id.tv_player);
        TextView playerNumber=(TextView)view.findViewById(R.id.tv_number);

        if (Util.notNull(item)){
            player.setText(item.getPlayerName());
            playerNumber.setText(item.getPlayerNo());
            view.setTag(item);
        }else {
            AgainstVo vo=new AgainstVo();
            view.setTag(vo.createItem());
        }



    }

    private void createView(ViewGroup.LayoutParams params){
        View view= inflater.inflate(R.layout.formation_player,null);
        view.setLayoutParams(params);
       /* TextView textView=new TextView(getContext());
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(textSize);
        if (flag) {
            textView.setBackgroundResource(R.drawable.shape_round_lineup);
        }else {
            textView.setBackgroundResource(R.drawable.icon_yellow_team);
        }
        textView.setLayoutParams(params);
        textView.setX(-params.width);
        textView.setY(-params.height);
        textView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);*/
        addView(view);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.onItemClick((AgainstVo.Item)v.getTag(),flag);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
       initData();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public FormationView(Context context) {
        super(context);
        init();
    }

    public FormationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FormationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    static {
        team=new HashSet();
        team.add(532);
        team.add(541);
        team.add(4222);
        team.add(442);
        team.add(41212);
        team.add(451);
        team.add(433);
        team.add(4411);
        team.add(4141);
        team.add(4231);
        team.add(4321);
        team.add(352);
        team.add(343);
        team.add(31312);
        team.add(3412);
        team.add(3421);
        team.add(3511);
        team.add(3142);
        team.add(424);
        team.add(4132);
        team.add(4312);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setTeamData(Map<Integer, AgainstVo.Item> teamMap,Formation formation) {
        if (!team.contains(formation.getFormation()))  return;
        this.formation=formation;
        this.teamMap = teamMap;
        initData();
    }

    public Map<Integer, AgainstVo.Item> getTeamMap() {
        return teamMap;
    }

    public Formation getFormation() {
        return formation;
    }


}