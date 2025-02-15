export class Game {

    private frames: Frame[];
    private currentFrame: Frame;

    constructor() {
        this.currentFrame = new Frame();
        this.frames = [];
    }

    score(): number {
        const currentFrameScore = this.frames.length >= 10 ? 0 : this.currentFrame.score();
        return currentFrameScore +
            this.frames.filter((_, index) => index < 10).map(it => it.score()).reduce(addNumbers, 0);
    }

    roll(n: number): void {
        const rollResult = this.currentFrame.roll(n);
        if (typeof rollResult === "object") {
            this.frames.push(this.currentFrame);
            this.currentFrame = rollResult;
        }
    };

    frame(): number {
        return Math.min(this.frames.length + 1, 10);
    }
}

class Frame {

    constructor() {
        this.rolls = []
    }

    nextFrame: Frame;

    rolls: number[];

    isCompleted(): boolean {
        return this.rolls.length == 2 || this.score() == 10
    }

    isSpare(): boolean {
        return this.rolls.length == 2 && this.rolls[0] + this.rolls[1] == 10
    }

    isStrike(): boolean {
        return this.rolls.length == 1 && this.rolls[0] == 10
    }

    score(): number {
        if (this.isSpare()) {
            if (this.nextFrame == undefined) {
                return 10;
            }
            return 10 + this.nextFrame.pinsDownOnNext(1)
        }
        if (this.isStrike()) {
            if (this.nextFrame == undefined) {
                return 10;
            }
            return 10 + this.nextFrame.pinsDownOnNext(1) + this.nextFrame.pinsDownOnNext(2);
        }
        return this.rolls.length == 0 ? 0 : this.rolls.reduce(addNumbers);
    }

    roll(n: number): Frame | void {
        this.rolls.push(n);
        if (this.isCompleted()) {
            const frame = new Frame();
            this.nextFrame = frame;
            return frame;
        }
    }

    pinsDownOnNext(throwNumber: number): number {
        if (this.rolls.length == 0) {
            return 0;
        }
        if (this.rolls.length == 1 && throwNumber > 1) {
            if (this.nextFrame == undefined) {
                return 0;
            }
            return this.nextFrame.pinsDownOnNext(1);
        }
        return this.rolls[throwNumber - 1];
    }
}

const addNumbers = (prev: number, curr: number) => prev + curr;