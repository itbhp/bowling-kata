export class Game {

    private frames: Frame[];
    private currentFrame: Frame;

    constructor() {
        this.currentFrame = new GenericFrame();
        this.frames = [];
    }
    score(): number {
        return this.frames.length == 0
            ? this.currentFrame.score()
            : this.frames.map(it => it.score()).reduce(addNumbers);
    }

    roll(n: number): void {
        this.currentFrame.roll(n);
        if (this.currentFrame.isCompleted()) {
            this.frames.push(this.currentFrame);
            this.currentFrame = new GenericFrame();
        }
    };

    frame(): number {
        return this.frames.length + 1;
    }
}

interface Frame {
    score(): number;
    roll(n: number): unknown;
    isCompleted(): boolean
}

class GenericFrame implements Frame {
    private rolls: number[]

    constructor() {
        this.rolls = []
    }
    score(): number {
        return this.rolls.reduce(addNumbers);
    }
    isCompleted(): boolean {
        return this.rolls.length >= 2 || this.score() >= 10
    }

    roll(n: number): void {
        this.rolls.push(n);
    }
}

const addNumbers = (prev: number, curr: number) => prev + curr;