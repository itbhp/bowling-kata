import { Game } from "./bowling-game";

describe("bowling game", () => {
    let game: Game;
    beforeEach(() => {
        game = new Game();
    });

    it("scores n pin in a roll", () => {
        game.roll(4);
        expect(game.score()).toBe(4);
    });
});