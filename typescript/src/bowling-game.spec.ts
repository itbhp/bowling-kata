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
        game.roll(4);
        game.roll(5);
        
        expect(game.frame()).toBe(2);
        expect(game.score()).toBe(9);
    });

    it("scores a spare, and then rolls < 10", () => {
        game.roll(4);
        game.roll(6);
        
        game.roll(3)
    
        expect(game.frame()).toBe(2);
        expect(game.score()).toBe(16);
    });

    it("scores a strike, and then normal frame, and a roll", () => {
        game.roll(10);

        game.roll(6);
        game.roll(2);
        
        game.roll(2);
        
        expect(game.frame()).toBe(3);
        expect(game.score()).toBe(28);
    });

    it("scores ten normal frames", () => {
        game.roll(1);
        game.roll(2); // score: 3

        game.roll(1);
        game.roll(4); // score: 8

        game.roll(1);
        game.roll(6); // score: 15

        game.roll(2);
        game.roll(1); // score: 18
        
        game.roll(2);
        game.roll(2); // score: 22
        
        game.roll(3);
        game.roll(3); // score: 28
        
        game.roll(2);
        game.roll(0); // score: 30
        
        game.roll(8);
        game.roll(1); // score: 39
        
        game.roll(1);
        game.roll(6); // score: 46
        
        game.roll(5);
        game.roll(4); // score: 55
        
        expect(game.frame()).toBe(10);
        expect(game.score()).toBe(55);
    });

    it("scores nine normal frames and a spare (+bonus roll)", () => {
        game.roll(1);
        game.roll(2); // score: 3

        game.roll(1);
        game.roll(4); // score: 8

        game.roll(1);
        game.roll(6); // score: 15

        game.roll(2);
        game.roll(1); // score: 18
        
        game.roll(2);
        game.roll(2); // score: 22
        
        game.roll(3);
        game.roll(3); // score: 28
        
        game.roll(2);
        game.roll(0); // score: 30
        
        game.roll(8);
        game.roll(1); // score: 39
        
        game.roll(1);
        game.roll(6); // score: 46
        
        game.roll(6);
        game.roll(4);
        game.roll(2); // score: 58
        
        expect(game.frame()).toBe(10);
        expect(game.score()).toBe(58);
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
        expect(game.score()).toBe(62);
    });

    it("scores perfect game", () => {
        game.roll(10); // 1 

        game.roll(10); // 2

        game.roll(10); // 3

        game.roll(10); // 4
        
        game.roll(10); // 5
        
        game.roll(10); // 6
        
        game.roll(10); // 7
        
        game.roll(10); // 8
        
        game.roll(10); // 9
        
        game.roll(10); // 10
        
        game.roll(10);
        game.roll(10);

        expect(game.frame()).toBe(10);
        expect(game.score()).toBe(300);
    });

    it("random game with multiple spares and strikes", () => {
        game.roll(1);
		game.roll(4); // 5

		game.roll(4);
		game.roll(5); // 5 + (4+5) = 14

		game.roll(6); 
		game.roll(4); // spare 14 + 10 + 5 = 29

		game.roll(5); 
		game.roll(5); // spare 29 + 10 + 10 = 49

		game.roll(10); // strike 49 + 10+ 0 + 1 = 60

		game.roll(0); 
		game.roll(1); //60 + 0 + 1 = 61

		game.roll(7);
		game.roll(3); // spare 61 + 10 + 6 = 77

		game.roll(6);
		game.roll(4); // spare 77 + 10 + 10 = 97

		game.roll(10); // strike 97 + 10 + 2+ 8 = 117

		game.roll(2);	
		game.roll(8); // spare 117 + 10 + 6 = 133
		game.roll(6);

        expect(game.frame()).toBe(10);
        expect(game.score()).toBe(133);
    });

});