XFilter is a very simple, easy-to-use chat filter plugin that replaces any words (or custom regex filters) on your configured list with asterisks (or a character of your choice). This includes blocked words with multiple letter uses, e.g. faaart.

# Commands /xfilter \<extension\>
Extensions:
- addWord \<word\> *Adds the word to your list.*
- removeWord \<word\> *Removes the word from your list.*
- list *Lists the currently filtered words.*
- setCharacter \<character\> *Sets the character that will replace letters.*
- addFilter \<regex filter\> \<number of characters to replace it with> *Adds the regex filter to your list.*
- removeFilter \<regex filter\> *Removes the filter from your list.*

# Examples
/xfilter addword farts - *Adds the word "farts" to the filter, "farts" would then be replaced by 5 of the replacement character you have set, by default this would be 5 '\*' symbols.*

/xfilter removeword farts - *Removes the word "farts" from the filter.*

/xfilter setCharacter o - *Sets the character replacement as "o", so farts would be filtered to "ooooo".*

# Permissions:
- xf.add
- xf.remove
- xf.list
- xf.setchar
- xf.*

As of XFilter 1.4.4 I have implemented auto-updating, this has not been fully tested but can be turned off by setting "autoUpdate" to 'false' in the filter.yml.
