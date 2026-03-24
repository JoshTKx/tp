# HomeChef Helper - Claude Code Instructions

## Project Overview
HomeChef Helper is a CLI-based order and customer tracking app for home-based F&B business owners. It is built on top of AddressBook Level 3 (AB3).

## Build & Test
- Build: `./gradlew build`
- Test: `./gradlew test`
- Run: `./gradlew run`

## Workflow Rules
- NEVER commit directly to `master`
- ALWAYS work on a feature branch checked out from the latest `master`
- Branch naming: `<type>-<short-description>` e.g. `refactor-rename-addressbook`
- Commit messages must be a single short imperative line e.g. `Add order validation`, `Fix duplicate check`, `Rename AddressBook to OrderBook`
- No Co-Authored-By trailers in commit messages
- One logical change per commit

## Code Style
- Follow existing AB3 code style
- No emojis in code or comments
- Javadoc required for all public methods

## Simplicity
- Prefer the simplest correct implementation; avoid algorithmic complexity unless clearly necessary
- No custom DP or graph algorithms when a simple string check suffices
- No fuzzy / "Did you mean" suggestions — exact case-insensitive matching only
- This applies to all new features on this branch and any future work

## Architecture
- 4 components: UI, Logic, Model, Storage
- Commands go in `logic/commands/`
- Parsers go in `logic/parser/`
- Model classes go in `model/`


## Current Task
Branch `feat-add-menu` is already checked out.
Do NOT create new branches or switch branches.
Do NOT push or open PRs.
Make all changes on the current branch only.

### Feature: Menu List
Implementing a menu of food items the owner sells, with order validation against the menu and a toggleable sidebar. Spec: `docs/superpowers/specs/2026-03-20-menu-design.md`.

**New model classes (`model/menu/`):**
- `MenuItemName.java` — validated non-empty string
- `Price.java` — validated positive decimal string
- `MenuItem.java` — immutable value object; name + price + availability; identity on name (case-insensitive)
- `UniqueMenuItemList.java` — enforces no duplicate names
- `MenuBook.java` / `ReadOnlyMenuBook.java` — root model class

**New storage classes:**
- `storage/JsonAdaptedMenuItem.java`
- `storage/JsonSerializableMenuBook.java`
- Menu persists in a separate `menu.json` file

**New commands:**
- `AddMenuCommand` / `AddMenuCommandParser` — `add-menu n/NAME x/PRICE [v/AVAILABILITY]`
- `EditMenuCommand` / `EditMenuCommandParser` — `edit-menu INDEX [n/NAME] [x/PRICE] [v/AVAILABILITY]`
- `DeleteMenuCommand` / `DeleteMenuCommandParser` — `delete-menu INDEX`
**New UI:**
- `MenuCard.java` + `MenuListCard.fxml`
- `MenuListPanel.java` + `MenuListPanel.fxml`
- `MainWindow.fxml` — content wrapped in permanent horizontal `SplitPane` (divider 0.7); menu always visible

**Files to modify:**
- `model/Model.java` + `ModelManager.java` — add menu CRUD/query methods
- `storage/Storage.java` + `StorageManager.java` — add menu read/save; `menu.json` path
- `MainApp.java` — load `menu.json` on startup
- `logic/parser/CliSyntax.java` — add `n/` (menu name), `x/` (price — `p/` is taken by phone), `v/` (availability)
- `logic/parser/HomeChefParser.java` — register new commands
- `logic/commands/AddCommand.java` + `EditCommand.java` — validate food name against menu at execute time
- `logic/Logic.java` + `LogicManager.java` — add `getFilteredMenuItemList()`; save menu in `execute()`

- `ui/MainWindow.java` — initialize `MenuListPanel`; inject into placeholder

**Key design rules:**
- Order validation runs at execute time (not parse time) — menu state only available in Model
- Hard block if food name not in menu (case-insensitive exact match only; no fuzzy suggestions)
- Unavailable items are also hard-blocked
- Deleting a menu item does not retroactively invalidate existing orders
- `EditCommand` only validates food name when `editOrderDescriptor.getFood().isPresent()`
- Stock tracking is out of scope for v1; availability flag is manual
- New prefixes: `n/` (menu name), `x/` (price — `p/` is taken by phone), `v/` (availability)
- `MenuItemName` uses same regex as `Food` so names are interchangeable
- `Price` regex: `[1-9][0-9]*(\\.[0-9]{1,2})?|0\\.[0-9]{1,2}` — no zero prices
- Storage follows sub-interface pattern: `MenuBookStorage` interface + `JsonMenuBookStorage` impl
- `ModelManager` 3-arg constructor accepts `ReadOnlyMenuBook`; `equals()` updated to include menu