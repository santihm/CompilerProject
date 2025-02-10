import java_cup.runtime.Symbol;

%%

%class Lexer
%cupsym sym
%cup

%state COMMENT
%state COMMENT_SINGLE

%{
// Helper methods to build symbols
private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
}

private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
}
%}

/* Regular Expressions */

// Whitespace, digits, letters, and common patterns
Whitespace = [ \t\f\r\n]+
Digit = [0-9]
Letter = [a-zA-Z_]
Identifier = {Letter}({Letter}|{Digit})*
IntegerLiteral = {Digit}+
DoubleLiteral = {Digit}+("."{Digit}+)?
StringLiteral = "\"" ([^\"\\\r\n]|\\[btnrf\"\\])* "\""
CharLiteral = '\'' ([^\'\\\r\n]|\\[btnrf\'\\]) '\'

// Tokens and reserved keywords

%%

<YYINITIAL> {

  // Ignore whitespaces
  {Whitespace}             { /* Ignore whitespace */ }

  // Reserved keywords
  "program"                { return symbol(sym.PROGRAM); }
  "begin"                  { return symbol(sym.BEGIN); }
  "end"                    { return symbol(sym.END); }
  "int"                    { return symbol(sym.INT); }
  "bool"                   { return symbol(sym.BOOL); }
  "double"                 { return symbol(sym.DOUBLE); }
  "string"                 { return symbol(sym.STRING); }
  "char"                   { return symbol(sym.CHAR); }
  "def"                    { return symbol(sym.DEF); }
  "return"                 { return symbol(sym.RETURN); }
  "true"                   { return symbol(sym.TRUE); }
  "false"                  { return symbol(sym.FALSE); }
  "if"                     { return symbol(sym.IF); }
  "then"                   { return symbol(sym.THEN); }
  "while"                  { return symbol(sym.WHILE); }
  "do"                     { return symbol(sym.DO); }
  "else"                   { return symbol(sym.ELSE); }
  "not"                    { return symbol(sym.NOT); }
  "and"                    { return symbol(sym.AND); }
  "or"                     { return symbol(sym.OR); }
  "ref"                    { return symbol(sym.REF); }

  // Identifiers
  {Identifier}             { return symbol(sym.ID, yytext()); }

  // Literals
  {IntegerLiteral}         { return symbol(sym.INT_CONST, Integer.parseInt(yytext())); }
  {DoubleLiteral}          { return symbol(sym.DOUBLE_CONST, Double.parseDouble(yytext())); }
  {StringLiteral}          { return symbol(sym.STRING_CONST, yytext()); }
  {CharLiteral}            { return symbol(sym.CHAR_CONST, yytext()); }

  // Separators
  "("                      { return symbol(sym.LPAR); }
  ")"                      { return symbol(sym.RPAR); }
  "{"                      { return symbol(sym.LBRAC); }
  "}"                      { return symbol(sym.RBRAC); }
  ","                      { return symbol(sym.COMMA); }
  ";"                      { return symbol(sym.SEMI); }
  ":"                      { return symbol(sym.COLON); }

  // Operators
  ":="                     { return symbol(sym.ASSIGN); }
  "="                      { return symbol(sym.ASSIGNDECL); }
  "=="                     { return symbol(sym.EQ); }
  "<>"                     { return symbol(sym.NE); }
  "<"                      { return symbol(sym.LT); }
  "<="                     { return symbol(sym.LE); }
  ">"                      { return symbol(sym.GT); }
  ">="                     { return symbol(sym.GE); }
  "+"                      { return symbol(sym.PLUS); }
  "-"                      { return symbol(sym.MINUS); }
  "*"                      { return symbol(sym.TIMES); }
  "/"                      { return symbol(sym.DIV); }
  "<<"                     { return symbol(sym.IN); }
  ">>"                     { return symbol(sym.OUT); }
  "!>>"                    { return symbol(sym.OUTNL); }
  "|"                      { return symbol(sym.PIPE); } // Added PIPE definition

  // Single-line comments
  "//"                     { yybegin(COMMENT_SINGLE); } // Switch to COMMENT_SINGLE state

  // Multi-line comments
  "/*"                     { yybegin(COMMENT); }

  // Error handling and illegal characters
  "\""                     { throw new Error("Unterminated string literal"); }
  [^]                      { return symbol(sym.ERROR, "Illegal character: " + yytext()); }
}

<COMMENT> {
  "*/"                     { yybegin(YYINITIAL); }
  [^\*]                    { /* Consume content inside the comment */ }
  \*[^/]                   { /* Ignore '*' not followed by '/' */ }
  <<EOF>>                  { throw new Error("Unclosed comment block"); }
}

<COMMENT_SINGLE> {
  [^\n]+                   { /* Consume line of comment */ }
  \n                       { yybegin(YYINITIAL); } // Return to initial state after a newline
}