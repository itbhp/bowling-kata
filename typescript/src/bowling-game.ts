export class Game {
    private rolls: number[];

    constructor() {
        this.rolls = [];
    }

    score(): number {
        return this.rolls.reduce((prev: number, curr: number) => prev + curr);
    }
    
    roll(n: number): void {
        this.rolls.push(n);
    };
}