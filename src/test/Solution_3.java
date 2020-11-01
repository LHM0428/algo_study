package test;

public class Solution_3 {

	//idx = 0 : ���� �Ʒ� �ﰢ��
	//idx = 1 : ���� �Ʒ� �ﰢ��
	//idx = 2 : ���� �� �ﰢ��
	//idx = 3 : ���� �� �ﰢ��
	int dRow[] = { 1, 1, -1, -1 };
	int dCol[] = { 1, -1, -1, 1};
	int dStartRow[] = { 0, 0, -1, -1 };
	int dStartCol[] = { 0, -1, -1, 0 };
	
	public int solution(int[][] maps, int p, int r) {
		int answer = 0;
		r = r/2;
		
		for(int startRow = r-1; startRow < maps.length-(r-1); startRow++) {
			for(int startColumn= r-1; startColumn < maps.length-(r-1); startColumn++) {
				int temp = 0;
				for(int i=0; i<4; i++) {
					temp += calc(startRow+dStartRow[i], startColumn+dStartCol[i], dRow[i], dCol[i], maps, p, r);
				}
				answer = Math.max(answer, temp);
			}
		}
		
		
		return answer;
	}
	private int calc(int startRow, int startColumn, int dRow, int dCol, int[][] maps, int p, int r) {
		int sum = 0;
		
		
		int moveRowCnt = 0;
		for(int i=startRow; moveRowCnt<r; i+=dRow) {
			if( i < 0 || i >= maps.length ) break;
			int moveColCnt = 0;
			
			for(int j=startColumn; moveRowCnt+moveColCnt<r; j+=dCol) {
				if(j < 0 || j >= maps.length) break;
				
				if(moveRowCnt+moveColCnt == r-1) { // ���� ���κ� �Ŀ� -> p/2
					if(maps[i][j] <= p/2) sum++;
				} else {
					if(maps[i][j] <= p) sum++;
				}
				moveColCnt++;
			}
			moveRowCnt++;
		}
		
		return sum;
	}
}
