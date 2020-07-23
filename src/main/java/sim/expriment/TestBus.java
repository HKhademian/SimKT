package sim.expriment;

import sim.test.TestKt;

public class TestBus {
	public static void main(String[] args) {
		// Bus bus = null;
		// bus.get(1);

		TestKt.test(() -> "hi", " this msg ", (it) -> {
			var x = it.chars().count();
			var y = x * it.length();
		});

		TestKt.testOn("me", "hello", () -> {
			var i = 2 + 2;
		});
	}
}
