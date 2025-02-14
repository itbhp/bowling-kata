export class Game {

    private frames: Frame[];
    private currentFrame: Frame;

    constructor() {
        this.currentFrame = new GenericFrame();
        this.frames = [];
    }
    score(): number {
        const currentFrameScore = this.currentFrame.score();
        return this.frames.length == 0
            ? currentFrameScore
            : currentFrameScore + this.frames.map(it => it.score()).reduce(addNumbers);
    }
    roll(n: number): void {
        const rollResult = this.currentFrame.roll(n);
        if (typeof rollResult === "object") {
            this.frames.push(this.currentFrame);
            this.currentFrame = rollResult;
        }
    };

    frame(): number {
        return this.frames.length + 1;
    }
}

interface Frame {
    score(): number;
    roll(n: number): Frame | void;
    rolls: number[]
}

class GenericFrame implements Frame {

    private nextFrame: Frame

    constructor() {
        this.rolls = []
    }

    rolls: number[];

    private isCompleted(): boolean {
        return this.rolls.length >= 2 || this.score() >= 10
    }
    private isSpare(): boolean {
        return this.rolls.length == 2 && this.rolls[0] + this.rolls[1] == 10
    }

    score(): number {
        const pinsRolledDown = this.rolls.length == 0 ? 0 : this.rolls.reduce(addNumbers);
        if (this.isSpare()) {
            const nextFrameRolls = this.nextFrame.rolls;
            return nextFrameRolls.length == 0 ? 10 : 10 + nextFrameRolls[0];
        }
        return pinsRolledDown;
    }

    roll(n: number): Frame | void {
        this.rolls.push(n);
        if (this.isCompleted()) {
            const frame = new GenericFrame();
            this.nextFrame = frame;
            return frame;
        }
    }
}

const addNumbers = (prev: number, curr: number) => prev + curr;