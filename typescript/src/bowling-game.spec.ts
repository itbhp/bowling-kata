import { Game } from "./bowling-game";

describe("bowling game", () => {
    let game: Game;
    beforeEach(() => {
        game = new Game();
    });

    it("scores n < 10 pin in a roll", () => {
        game.roll(4);
        expect(game.score()).toBe(4);
    });

    it("scores n < 10 pin in two rolls", () => {
        game.roll(4);
        game.roll(5);
        expect(game.score()).toBe(9);
    });
});