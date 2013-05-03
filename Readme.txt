Javachess usage notes:
======================

* The batch file contains an example of how to invoke the code: pass it
  an opening book as first command-line parameter, and a position 
  description file as (optional) second parameter.  Without a position file
  the program begins in the usual chess starting position.

* This is a console-type application.  There is no GUI.  None.  Java makes
  a window pop-up at start-up time, but you can ignore it.  I know I do.

* This code is mostly intended as didactic fodder, and not as a playable game.
  However, if you insist, you can compile the code with JDK 1.2.2 or above.  
  Older versions may work, but I don't guarantee it.

* You need to feed your moves to the program in a somewhat cryptic way.  The 
  square at the top-left corner of the board (Black Queen's rook) is named 00,
  and the others are numbered a row at a time until you reach 63.  Even worse:
  you must use two digits for each square number, even those numbered 0 to 9.
  For example, moving the black queen's knight from its original spot to the
  square immediately in front of the queen's bishop's pawn would require the
  following entry: "01 18".  One space in between.  No more, no less.

* The format of an opening book file is the following: 

  - First line: number of lines of play in the book.
  - For each line of play, a number of moves in "source-destination" pair 
    format, followed by the keyword END

* The format of a starting position file is the following:

  - Who will move next: WHITE or BLACK
  - Number of pieces on the board
  - For each piece, its two-character ID followed by its starting square
  - Four string constants determining whether castling is legal for each
    player on each side, in the following order: Black Kingside, White
    Kingside, Black Queenside, White Queenside.
  - An integer constant marking the position of the "en passant" pawn, 
    if any.  If you are nuts enough to want to try this, look at the
    code, otherwise leave it at 0 (no such pawn) ;)
