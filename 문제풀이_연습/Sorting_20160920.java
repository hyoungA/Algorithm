/*
���ĸ����ε� ������ Ǯ���� ��찡 ����

N^2 -> ��������, ��������, X
NlogN -> ����Ʈ, ������Ʈ, ����Ʈ(���� ����)

- ������Ʈ
  - (�߾ӱ�������) �ݾ� ��� ������, �迭 N �� M �� ���ļ� ũ�� N + M �� �迭�� �����Ѵ�.
  - ���� �����ؼ� �۾��ؾ� �ϹǷ�, �޸𸮰� �ʿ��ϴ�. 

- �W��Ʈ
  - �ϳ��� ���Ҹ� ���, �迭 �� ���Һ��� ���� �׷�� ū �׷����� �������ش�.
 */
public class Sorting_20160920 {
	static int[] numbers = new int[]{3, 5, 1, 30, 14,26, 50};
	
	public static void main(String[] args) {
		mergeSort(numbers, 0 , numbers.length -1);
		
		for(int i =0;i<numbers.length;i++){
			System.out.println(numbers[i]);			
		}
	}
	
	static void mergeSort(int[] arr, int p, int r){
		if(p < r){
			int mid = (p + r) /2;
			mergeSort(arr, p, mid);
			mergeSort(arr, mid + 1, r);
			merge(arr, p, mid, r);
		}
	}
	
	static void merge(int arr[], int p, int mid, int r){
		int i = p, j = mid + 1, t = 1;
		
		System.out.println("p = " + p + " mid = " + mid + " r = " + r);
		
		// ���ο� �迭 ����
		int[] tmp = new int[arr.length + 1];
		
		while (i <= mid && j <= r) {
			// �迭�� ���� ���Ұ� �迭�� ������ �۰ų� ���ٸ�
			if (arr[i] <= arr[j]) {
				// tmp[t]�� arr[i]���Ҹ� ����ְ� ++;
				tmp[t++] = arr[i++];
			} else {
				// �ƴ϶�� �����Ҹ� �ְ� j�� ++;
				tmp[t++] = arr[j++];
			}
		}
		
		//���� �κ� �迭�� �������
		while (i <= mid)
			tmp[t++] = arr[i++];

		// ������ �κ��� ���� ���
		while (j <= r)
			tmp[t++] = arr[j++];
		i = p;
		t = 1;

		// ����� arr�� ����
		while (i <= r)
			arr[i++] = tmp[t++];
	}
}
