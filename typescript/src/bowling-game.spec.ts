import { Game } from "./bowling-game";

describe("bowling game", () => {
    let game: Game;
    beforeEach(() => {
        game = new Game();
    });

    it("scores n < 10 pin in a roll", () => {
        expect(game.frame()).toBe(1);
        game.roll(4);
        expect(game.frame()).toBe(1);
        expect(game.score()).toBe(4);
    });

    it("scores n < 10 pin in two rolls", () => {
        expect(game.frame()).toBe(1);
        game.roll(4);
        expect(game.frame()).toBe(1);
        game.roll(5);
        expect(game.frame()).toBe(2);
        expect(game.score()).toBe(9);
    });

    it("scores a spare, and then rolls < 10", () => {
        game.roll(4);
        expect(game.frame()).toBe(1);
        game.roll(6);
        game.roll(3)
        expect(game.frame()).toBe(2);
        expect(game.score()).toBe(16);
    });
});