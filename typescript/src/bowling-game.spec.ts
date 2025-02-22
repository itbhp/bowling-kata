import { Game } from "./bowling-game";
    
describe("bowling game", () => {
    let game: Game;
    beforeEach(() => {
        game = new Game();
    });

    it("scores n < 10 pins in a roll", () => {
        expect(game.playingFrame()).toBe(1);
        roll(4);

        expect(game.playingFrame()).toBe(1);
        expect(game.score()).toBe(4);
    });

    it("scores n < 10 pins in two rolls", () => {
        roll(4, 5);
        
        expect(game.playingFrame()).toBe(2);
        expect(game.score()).toBe(9);
    });

    it("scores a spare, and then rolls < 10", () => {
        roll(4, 6, 3)
    
        expect(game.playingFrame()).toBe(2);
        expect(game.score()).toBe(13 + 3);
    });

    it("scores a strike, and then normal frame, and a roll", () => {
        roll(10, 6, 2, 2)
        
        expect(game.playingFrame()).toBe(3);
        expect(game.score()).toBe(18 + 8 + 2);
    });

    it("scores ten normal frames", () => {
        roll(1, 2, 1, 4, 1, 6, 2, 1, 2, 2, 3, 3, 2, 0, 8, 1, 1, 6, 5, 4)
        
        expect(game.playingFrame()).toBe(10);
        expect(game.score()).toBe(3 + 5 + 7 + 3 + 4 + 6 + 2 + 9 + 7 + 9); // 55
    });

    it("scores nine normal frames and a spare (+bonus roll)", () => {
        roll(1, 2, 1, 4, 1, 6, 2, 1, 2, 2, 3, 3, 2, 0, 8, 1, 1, 6, 6, 4, 2)
        
        expect(game.playingFrame()).toBe(10);
        expect(game.score()).toBe(3 + 5 + 7 + 3 + 4 + 6 + 2 + 9 + 7 + 12); // 58 
    });

    it("scores nine normal frames, a strike plus two bonus rolls", () => {
        roll(1, 2, 1, 4, 1, 6, 2, 1, 2, 2, 3, 3, 2, 0, 8, 1, 1, 6, 10, 4, 2)
        
        expect(game.playingFrame()).toBe(10);
        expect(game.score()).toBe(3 + 5 + 7 + 3 + 4 + 6 + 2 + 9 + 7 + 16); // 62
    });

    it("scores perfect game", () => {
        roll(...new Array<number>(12).fill(10))

        expect(game.playingFrame()).toBe(10);
        expect(game.score()).toBe(300);
    });

    it("random game with multiple spares and strikes", () => {
        roll(1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6)

        expect(game.playingFrame()).toBe(10);
        expect(game.score()).toBe(5 + 9 + 15 + 20 + 11 + 1 + 16 + 20 + 20 + 16); // 133 
    });

    function roll(...pinsRolls: number[]) {
        pinsRolls.forEach(pins => game.roll(pins));
    }
});