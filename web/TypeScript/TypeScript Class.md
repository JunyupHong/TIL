# TypeScript Class
* JavaScript는 재사용 가능한 컴포넌트를 만들기 위해 함수와 프로토 타입 기반 상속을 사용
* TypeScript는 클래스를 이용해 상속을 사용


## Class
* Java의 class와 유사
* 클래스의 프로퍼티에 접근하려면 this를 사용
* 클래스의 인스턴스는 new를 이용해서 생성
* 인스턴스가 생성될 때 생성자를 호출하여 새로운 객체를 생성하고 초기화
``` typescript
class Greeter {
	greeting: string;
	constructor(message: string) {
		this.greeting = message;
	}
	greet() {
		return "Hello, " + this.greeting;
	}
}
console.log (new Greeter("world").greet());
```



## 상속
* TypeScript에서는 객체지향 패턴을 사용할 수 있다
* 객체 지향 패턴의 가장 기본적인 패턴: 상속
* 상속을 사용하면 기존의 클래스를 확장하여 새로운 클래스를 생성할 수 있다

* 생성자 함수를 포함하는 상속받는 클래스는 기본 클래스에서 생성자 함수를 실행할 수 있게 무조건 super()를 호출해야한다
``` typescript
class Animal {
	name: string;
	constructor(theName: string) {
		this.name = theName;
	}
	move(distanceInMeters: number = 0) {
		console.log(`${this.name} moved ${distanceInMeters}m.`);
	}
}

class Snake extends Animal {
	constructor(name: string) {
		super(name);	// Animal의 생성자함수 호출!
	}
	move(distanceInMeters = 5) {
		console.log("Slithering...");
		super.move(distanceInMeters);
	}
}

class Horse extends Animal {
	constructor(name: string) {
		super(name);	// Animal의 생성자함수 호출!
	}
	move(distanceInMeters = 45) {
		console.log("Galloping...");
		super.move(distanceInMeters);
	}
}

let snake = new Snake("snake1");
let horse: Animal = new Horse("horse1");

snake.move();		// Slithering...
					// snake1 moved 5m.

horse.move(34);	// Galloping...
					// Tommy the Palomino moved 34m.
```



## 접근 지정자
* public, private, protected
* default값은 public이다

### public
* default 값
* 어디서든 접근 가능

### private
* 클래스 외부에서 접근 불가

### protected
* 상속받은 클래스 내부에서만 접근 가능

* 생성자를 protected로 지정할 수 있음
	* => 클래스 외부에서 인스턴스화 할 수 없지만 확장 가능
``` typescript
class Person {
	protected name: string;

	// 생성자가 protected
	protected constructor(theName: string){
		this.name = theName;
	}
}

// Person 상속
class Employee extends Person {
	private department: string;
	constructor(name: string, department: string) {
		super(name);
		this.department = department;
	}
}

let howard = new Employee("Howard", "Sales");
let john = new Person("John"); // Error
				// Person의 생성자가 protected이다
```


> TypeScript는 구조형 시스템  
> => 두 타입을 비교할때 구성원의 타입이 호환가능하면 타입 자체가 호환가능  
> 하지만 접근 지정자가 private 또는 protected 인 멤버를 갖는 타입을 비교할때 다르게 인식한다 (호환이 안된다)  
``` typescript
class Animal {
	private name: string;
	constructor(theName: string) {
		this.name = theName;
	}
}

class Rhino extends Animal {
	constructor() { super("Rhino"); }
}

class Employee {
	private name: string;
	constructor(theName: string) {
		this.name = theName;
	}
}

let animal = new Animal("Goat");
let rhino = new Rhino();
let employee = new Employee("Bob");

animal = rhino;	// animal과 rhino는 서로 호환 가능
					// 서로 private 변수를 공유
animal = employee;	// error! 호환 불가
					// => animal의 employee가 각각 private 변수를 갖는다
```


### readonly
* 읽기 전용 속성
* 읽기 전용 속성을 지정하면 선언 또는 생성자에서 초기화 해야한다



## 파라미터 프로퍼티
* 한번에 프로퍼티를 만들고 초기화 하는 방법
``` typescript
class Octopus {
	constructor(private priName: string, public pubName:string, readonly roName:string) {
		// 파라미터 프로퍼티로 private, public, readonly
		// 변수를 선언&초기화 한다
	}
}
```



## Getter/Setter (Accessor)
* TypeScript에서 객체의 멤버에 대한 액세스를 지원하는 메소드
* private 변수를 외부에서 접근/수정하는 방법
* 컴파일러를 ECMAScript 5 이상을 지원하도록 설정해야한다
* get, set이 없는 프로퍼티는 자동으로 읽기전용으로 추정된다
	* .d.ts파일을 생성할 때 유용
``` typescript
class Employee {
	private _fullName: string;
	get fullName(): string {
		return this._fullName;
	}
	set fullName(newName: string) {
		this._fullName = newName;
	}
}
let employee = new Employee();
employee.fullName = "Bob Smith";

console.log(employee.fullName);	// Bob smith
```


## static 프로퍼티
* 클래스의 정적 멤버 => 모든 인스턴스가 공동으로 사용하는 변수
* 클래스 이름으로 접근
``` typescript
class Grid {
	static origin = {x: 0, y: 0};
	calculateDistanceFromOrigin(point: {x: number; y: number;}) {
		let xDist = (point.x - Grid.origin.x);
		let yDist = (point.y - Grid.origin.y);
		return Math.sqrt(xDist * xDist + yDist * yDist) / this.scale;
	}
	constructor (public scale: number) { }
}

let grid1 = new Grid(1.0);  // 1x scale
let grid2 = new Grid(5.0);  // 5x scale
console.log(grid1.calculateDistanceFromOrigin({x: 10, y: 10}));
console.log(grid2.calculateDistanceFromOrigin({x: 10, y: 10}));
```



## 추상클래스 (Abstract Class)
* 추상클래스는 다른 클래스를 파생시킬 수 있는 기본 클래스
* 직접 인스턴스화 할 수 없다
* 인터페이스와 달리 추상클래스는 멤버에 대한 Implementation 세부정보를 포함할 수 있다

``` typescript
abstract class Animal {
	abstract makeSound(): void;
	move(): void {
		console.log("moving...");
	}
}
```

* abstract로 표시된 추상 클래스 내의 메소드에는 Implementation이 포함되어 있지 않으므로 파생 클래스에서 구현해야한다 => 추상클래스를 상속받는 클래스는 반드시 추상 메소드를 구현해야한다
* 추상메소드는 인터페이스 메소드와 유사한 구문을 사용
	* 그러나 추상메소드는 abstract 키워드를 포함해야하고, 선택적으로 Getter/Setter를 포함훌 수 있다
``` typescript
abstract class Department {
	constructor(public name: string) {
	}
	printName(): void {
		console.log("Department name: " + this.name);
	}
	abstract printMeeting(): void; // 상속받는 클래스에서 반드시 구현되야한다
}

class AccountingDepartment extends Department {
	constructor() {
		// super()를 통해 부모클래스의 생성자를 실행
		super("Accounting and Auditing");
	}

	// 추상메소드 구현 (필수)
	printMeeting(): void {
		console.log("The Accounting Department meets each Monday at 10am.");
	}
	generateReports(): void {
		console.log("Generating reports...");
	}
}

// abstract type을 참조하는 것은 가능
let department: Department;

// error 추상클래스는 인스턴스를 생성할 수 없다
department = new Department();

// 일반 클래스의 인스턴스 생성
department = new AccountingDepartment();
department.printName();
department.printMeeting();

// department는 추상클래스 Department를 구현하고 있으므로 
// AccountingDepartment에서 선언된 generateReports는 존재하지 않는다
department.generateReports();
```




## 고급기술
### 생성자 함수
#### 클래스를 사용하는 방법
* 클래스 인스턴스 타입을 사용 (instance 측면)
	* => 생성자 함수를 통해 클래스의 인스턴스를 얻는다
```typescript
// 클래스 인스턴스 타입 사용
class Greeter {
	greeting: string;
	constructor(message: string) {
		this.greeting = message;
	}
	greet() {
		return "Hello, " + this.greeting;
	}
}
let greeter: Greeter;		// 클래스의 인스턴스 타입!
greeter = new Greeter("world");
console.log(greeter.greet());
```

* 클래스를 직접 사용 (static 측면)
	* => 인스턴스 유형이 아닌 “클래스 자체”의 타입을 사용
	* => 생성자 함수의 타입을 사용
``` typescript
class Greeter {
	static standardGreeting = "Hello, there";
	greeting: string;
	greet() {
		if (this.greeting) {
			return "Hello, " + this.greeting;
		}
		else {
			return Greeter.standardGreeting;
		}
	}
}

let greeter1: Greeter;
greeter1 = new Greeter();
console.log(greeter1.greet());
let greeterMaker: typeof Greeter = Greeter;
greeterMaker.standardGreeting = "Hey there!";
let greeter2: Greeter = new greeterMaker();
console.log(greeter2.greet());
```
	* greeter1은 클래스 인스턴스와 유사하게 작동 (클래스를 인스턴스화 하고 그 객체를 사용)
	* greeterMaker 는 클래스 자체를 보유하거나 다른 방법으로 생성자 함수를 나타낸다 => typeof Greeter 를 이용
	* => 인스턴스 유형이 아닌 “Greeter 클래스 자체”의 타입(생성자 함수의 타입)을 사용
	* Greeter 클래스의 인스턴스를 생성하는 생성자와 Greeter의 정적 멤버를 포함
	* greeterMaker를 통해 Greeter 클래스의 static변수에 접근할 수 있다


### 클래스를 인터페이스로 사용
* 클래스 선언은 클래스의 인스턴스를 나타내는 타입과 생성자 함수 를 작성해야한다
* 클래스는 타입을 생성하기 때문에 인터페이스를 사용할 수 있는 동일한 장소에서 이 타입을 사용할 수 있다
``` typescript
class Point {
	x: number;
	y: number;
}
interface Point3d extends Point {
	z: number;
}
let point3d: Point3d = { x: 1, y: 2, z: 3 };
```









