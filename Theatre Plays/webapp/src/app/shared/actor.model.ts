export class Actor {
  id: number;
  name: string;
  age: number;
  gender: string;

  // constructor(name: string, age: number, gender: string) {
  //   this.name = name;
  //   this.age = age;
  //   this.gender = gender;
  // }

  public constructor(init?: Partial<Actor>) {
    Object.assign(this, init);
  }
}
