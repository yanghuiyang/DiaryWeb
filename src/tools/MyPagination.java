package tools;

import java.util.ArrayList;
import java.util.List;

import model.Diary;

public class MyPagination {
	public List<Diary> list =null;
	private int recordCount=0;
	private int pagesize=0;
	private int maxPage=0;
	public List<Diary> getIntPage(List<Diary> list,int Page,int pagesize){
			List <Diary> newList=new ArrayList<Diary>();
			this.list=list;
			recordCount=list.size();
			this.pagesize=pagesize;
			this.maxPage=getMaxPage();
			for(int i=(Page-1)*pagesize;i<Page*pagesize-1;i++){
				if(i>=recordCount){
					break;
				}
				newList.add((Diary)list.get(i));
				
			}
			return newList;
	}
	public int getMaxPage() {
		// TODO Auto-generated method stub
		int maxPage=(recordCount%pagesize==0)?(recordCount%pagesize):(recordCount/pagesize+1);
		
		return maxPage;
	}
	public List<Diary>getAppointPage(int Page){
		List<Diary> newList=new ArrayList<Diary>();
		for(int i=(Page-1)*pagesize;i<Page*pagesize-1;i++){
			if(i>=recordCount){
				break;
			}
			newList.add((Diary)list.get(i));
			
		}
		return newList;
	}
	public int getRecordSize(){
		return recordCount;
	}
	public int getPage(String str){
		if(str==null){
			str="0";
		}
		int Page=Integer.parseInt(str);
		if(Page<1){
			Page=1;
		}else{
			if(((Page-1)*pagesize+1)>recordCount){
				Page=maxPage;
			}
			
		}
		return Page;
	}
	public String printCtrl(int Page,String url,String para){
		String strHtml="<table width='100%'  border='0' cellspacing='0' cellpadding='0'><tr> <td height='24' align='right'>当前页数：【"+Page+"/"+maxPage+"】&nbsp;";
		try{
		if(Page>1){
			strHtml=strHtml+"<a href='"+url+"&Page=1"+para+"'>第一页</a>　";
			strHtml=strHtml+"<a href='"+url+"&Page="+(Page-1)+para+"'>上一页</a>";
		}
		if(Page<maxPage){
			strHtml=strHtml+"<a href='"+url+"&Page="+(Page+1)+para+"'>下一页</a>　<a href='"+url+"&Page="+maxPage+para+"'>最后一页&nbsp;</a>";
		}
		strHtml=strHtml+"</td> </tr>	</table>";
		}catch(Exception e){
			e.printStackTrace();
		}
		return strHtml;
	}	
}
