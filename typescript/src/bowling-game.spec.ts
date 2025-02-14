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

    it("scores a strike, and then normal frame, and a roll", () => {
        expect(game.frame()).toBe(1);
        game.roll(10);
        expect(game.frame()).toBe(2);
        game.roll(6);
        expect(game.frame()).toBe(2);
        game.roll(2);
        expect(game.frame()).toBe(3);
        game.roll(2);
        expect(game.frame()).toBe(3);
        expect(game.score()).toBe(28);
    });

    it("scores ten normal frames", () => {
        game.roll(1);
        game.roll(2);

        game.roll(1);
        game.roll(4);

        game.roll(1);
        game.roll(6);

        game.roll(2);
        game.roll(1);
        
        game.roll(2);
        game.roll(2);
        
        game.roll(3);
        game.roll(3);
        
        game.roll(2);
        game.roll(0);
        
        game.roll(8);
        game.roll(1);
        
        game.roll(1);
        game.roll(6);
        
        game.roll(5);
        game.roll(4);
        
        expect(game.frame()).toBe(10);
        expect(game.score()).toBe(55);
    });

    it("scores nine normal frames and a spare (+bonus roll)", () => {
        game.roll(1);
        game.roll(2);

        game.roll(1);
        game.roll(4);

        game.roll(1);
        game.roll(6);

        game.roll(2);
        game.roll(1);
        
        game.roll(2);
        game.roll(2);
        
        game.roll(3);
        game.roll(3);
        
        game.roll(2);
        game.roll(0);
        
        game.roll(8);
        game.roll(1);
        
        game.roll(1);
        game.roll(6);
        
        game.roll(6);
        game.roll(4);
        game.roll(2);
        
        expect(game.frame()).toBe(10);
        expect(game.score()).toBe(60);
    });

    it("scores nine normal frames, a strike plus two bonus rolls", () => {
        game.roll(1);
        game.roll(2);

        game.roll(1);
        game.roll(4);

        game.roll(1);
        game.roll(6);

        game.roll(2);
        game.roll(1);
        
        game.roll(2);
        game.roll(2);
        
        game.roll(3);
        game.roll(3);
        
        game.roll(2);
        game.roll(0);
        
        game.roll(8);
        game.roll(1);
        
        game.roll(1);
        game.roll(6);
        
        game.roll(10);
        game.roll(4);
        game.roll(2);
        
        expect(game.frame()).toBe(10);
        expect(game.score()).toBe(68);
    });

});