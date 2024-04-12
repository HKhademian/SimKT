to contribiute:

1. clone project
2. import project in intellij
3. have fun

Examples: 

```kotlin
val A = 7.toBus(8)
val B = 11.toBus(8)

println("""
	A: ${A}
	B: ${B}
	!A:  ${A.not()}
	A and B: ${A and B}
	A nand B: ${A nand B}
	A or B: ${A or B}
	A nor B: ${A nor B}
	A xor B: ${A xor B}
	A xnor B: ${A xnor B}
""".trimIndent())
```


```kotlin
	test("loop on Not") {
		val lastGate = mut(false)
		val gate = not(lastGate)
		lastGate.set(gate)

		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)

	}

	test("loop on Nand") {
		val lastGate = mut(false)
		val gate = nand(listOf(lastGate, const(true)))
		lastGate.set(gate)

		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)
	}

	test("loop on Xor") {
		val lastGate = mut(false)
		val gate = xor(listOf(lastGate, const(true)))
		lastGate.set(gate)

		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)
		println(gate)
	}
```



```kotlin
	val data = mut(false)
	val en = mut(false)
	val q = mut(true)
	val and1 = and(listOf(data, en))
	val and2 = and(listOf(not(data), en))
	q.set(nor(listOf(and2, nor(listOf(q, and1)))))

	test("d latch en=0 data=0") {
		en.set(false); data.set(false);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=0 data=1") {
		en.set(false); data.set(true);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=1 data=1") {
		en.set(true); data.set(true);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=1 data=0") {
		en.set(true); data.set(false);
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
		println(q)
	}

	test("d latch en=1") {
		en.set(true)

		data.set(false)
		println(q)
		println(q)

		data.set(true)
		println(q)
		println(q)

		data.set(false)
		println(q)
		println(q)
	}
```



```kotlin
	val A = const(false)
	val B = const(true)
	val C = const(true)
	val D = const(false)
	val res = and(xor(or(A, C), D), C, nor(A, B))
	test("benchmark") {
		repeat(100000) {
			res.get()
		}
	}
```

