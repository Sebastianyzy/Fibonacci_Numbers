public class fibonacci {
	public static void main(String args[]) {
		System.out.println("print out fibonacci number up to 500:");
		for (int i = 0; i <= 500; i++) {
			String f = Integer.toString(i);
			System.out.println("F(" + f + ") is:" + " " + fib(f));
		}
	}

	public static String fib(String n) {
		if (n.equals("0")) {
			return n;
		}
		if (n.equals("1") || n.equals("2")) {
			return n;
		}
		String[][] A = new String[][] { { "1", "1" }, { "1", "0" } };
		matrixPower(A, Integer.toString(Integer.parseInt(n) - 1));

		return A[0][0];
	}

	public static void matrixPower(String[][] A, String n) {
		if (Integer.parseInt(n) <= 1) {
			return;
		}
		matrixPower(A, Integer.toString(Integer.parseInt(n) / 2));
		multiplyMatrix(A, A);

		String[][] B = new String[][] { { "1", "1" }, { "1", "" } };
		if (Integer.parseInt(n) % 2 != 0) {
			multiplyMatrix(A, B);
		}
	}

	public static void multiplyMatrix(String[][] A, String[][] B) {
		String x = plus(multiplyInt(A[0][0], B[0][0]), multiplyInt(A[0][1], B[1][0]));
		String y = plus(multiplyInt(A[0][0], B[0][1]), multiplyInt(A[0][1], B[1][1]));
		String z = plus(multiplyInt(A[1][0], B[0][0]), multiplyInt(A[1][1], B[1][0]));
		String w = plus(multiplyInt(A[1][0], B[0][1]), multiplyInt(A[1][1], B[1][1]));
		A[0][0] = x;
		A[0][1] = y;
		A[1][0] = z;
		A[1][1] = w;
	}

	public static String multiplyInt(String num1, String num2) {
		int len1 = num1.length();
		int len2 = num2.length();
		if (len1 == 0 || len2 == 0) {
			return "0";
		}

		int result[] = new int[len1 + len2];

		int i_n1 = 0;
		int i_n2 = 0;

		for (int i = len1 - 1; i >= 0; i--) {
			int carry = 0;
			int n1 = num1.charAt(i) - '0';
			i_n2 = 0;

			for (int j = len2 - 1; j >= 0; j--) {

				int n2 = num2.charAt(j) - '0';
				int sum = n1 * n2 + result[i_n1 + i_n2] + carry;
				carry = sum / 10;
				result[i_n1 + i_n2] = sum % 10;

				i_n2++;
			}

			if (carry > 0) {
				result[i_n1 + i_n2] += carry;
			}
			i_n1++;
		}

		int i = result.length - 1;
		while (i >= 0 && result[i] == 0) {
			i--;
		}

		if (i == -1) {
			return "0";
		}

		StringBuilder s = new StringBuilder();

		while (i >= 0) {
			s.append(result[i--]);
		}
		return s.toString();
	}

	public static String plus(String num1, String num2) {
		int max = (num1.length() < num2.length() ? num2.length() : num1.length());

		int[] n1 = new int[max];
		int[] n2 = new int[max];

		for (int i = 0; i < num1.length(); i++) {
			n1[i] = num1.charAt(num1.length() - 1 - i) - 48;
		}

		for (int i = 0; i < num2.length(); i++) {
			n2[i] = num2.charAt(num2.length() - 1 - i) - 48;
		}
		int carry = 0;
		int sum[] = new int[max + 1];
		for (int i = 0; i < max; i++) {
			sum[i] = ((n1[i] + n2[i]) + carry) % 10;
			if (((n1[i] + n2[i]) + carry) >= 10) {
				carry = 1;
			} else {
				carry = 0;
			}

		}
		sum[max] = carry;
		StringBuilder s = new StringBuilder();
		int zeros = sum.length - 1;
		while (sum[zeros] == 0) {
			zeros--;
		}
		for (int i = zeros; i >= 0; i--) {
			s.append(sum[i]);
		}
		return s.toString();

	}

}
