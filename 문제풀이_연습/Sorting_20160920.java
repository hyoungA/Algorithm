/*
정렬만으로도 문제가 풀리는 경우가 있음

N^2 -> 버블정렬, 삽입정렬, X
NlogN -> 퀵소트, 머지소트, 힙소트(사용빈도 적음)

- 머지소트
  - (중앙기준으로) 반씩 계속 나누고, 배열 N 와 M 을 합쳐서 크기 N + M 의 배열을 생성한다.
  - 값을 복사해서 작업해야 하므로, 메모리가 필요하다. 

- 큌소트
  - 하나의 원소를 잡고, 배열 중 원소보다 작은 그룹과 큰 그룹으로 나누어준다.
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
		
		// 새로운 배열 생성
		int[] tmp = new int[arr.length + 1];
		
		while (i <= mid && j <= r) {
			// 배열의 시작 원소가 배열의 끝보다 작거나 같다면
			if (arr[i] <= arr[j]) {
				// tmp[t]에 arr[i]원소를 집어넣고 ++;
				tmp[t++] = arr[i++];
			} else {
				// 아니라면 끝원소를 넣고 j를 ++;
				tmp[t++] = arr[j++];
			}
		}
		
		//왼쪽 부분 배열이 남은경우
		while (i <= mid)
			tmp[t++] = arr[i++];

		// 오른쪽 부분이 남은 경우
		while (j <= r)
			tmp[t++] = arr[j++];
		i = p;
		t = 1;

		// 결과를 arr에 저장
		while (i <= r)
			arr[i++] = tmp[t++];
	}
}
