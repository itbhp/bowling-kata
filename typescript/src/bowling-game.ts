export class Game {
    private rolls: number[];

    constructor(){
        this.rolls = [];
    }

    score(): number {
        return this.rolls[0];
    }
    roll(n: number): void {
        this.rolls.push(n);
    };
}