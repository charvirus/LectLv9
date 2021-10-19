package controller;

public class Shop {
	ItemManager im = new ItemManager();
	UserManager um = new UserManager();

	public void mainMenu() {
		boolean run = true;
		while (run) {
			System.out.println("[1.가입]\n[2.탈퇴]\n[3.로그인]\n[4.로그아웃]\n[100.관리자] [0.종료] ");
			int sel = UserManager.scan.nextInt();
			if (sel == 1) {
				um.join();
			} else if (sel == 2) {
				um.withdraw();
			} else if (sel == 3) {
				if (um.login()) {
					loginMenu();
				}
			} else if (sel == 4) {
				um.logout();
			} else if (um.adminLog && sel == 100) {
				managerMenu();
			} else if (sel == 0) {
				break;
			}
		}

	}

	private void loginMenu() {
		boolean run = true;
		while (run) {
			System.out.println("[1. 쇼핑]\n[2. 장바구니목록]\n[0. 뒤로가기]");
			int sel = UserManager.scan.nextInt();
			if (sel == 1) {
				shopMenu();
			} else if (sel == 2) {
				cartMenu();
			} else if (sel == 0) {
				break;
			}
		}
	}

	private void managerMenu() {
		boolean run = true;
		while (run) {
			System.out.println("[1. 아이템 관리]\n[2. 카테고리 관리]\n[3. 장바구니 관리]\n[4. 유저 관리]\n[0.뒤로가기] ");
			int sel = UserManager.scan.nextInt();
			if (sel == 1) {
				itemMenu();
			} else if (sel == 2) {
				categoryMenu();
			} else if (sel == 3) {
				im.adminprintJang();
			} else if (sel == 4) {
				userMenu();
			} else if (sel == 0) {
				run = false;
			}
		}
	}

	private void cartMenu() {
		boolean run = true;
		while (run) {
			System.out.println("[1. 내 장바구니]\n[2. 삭제]\n[3. 구입]\n[0. 뒤로가기]");
			int sel = UserManager.scan.nextInt();
			if (sel == 1) {
				im.printJang(um.getUserList().get(um.userLog));
			} else if (sel == 2) {
				int cnt = im.itemListCnt(um.getUserList().get(um.userLog));
				System.out.println("아이템 개수 : " + cnt);
				if (cnt != 0) {
					im.printJang(um.getUserList().get(um.userLog));
					System.out.println("삭제할 아이템을 선택해주세요.");
					int inp = UserManager.scan.nextInt();
					if (inp >= 0 && inp < im.itemListCnt(um.getUserList().get(um.userLog))) {
						im.delJangItem(inp);
					}else {
						System.out.println("입력을 다시 해주세요.");
					}
				} else {
					System.out.println("장바구니가 비었습니다.");
				}
			} else if (sel == 3) {
				int sum = im.itemListTotal(um.getUserList().get(um.userLog));
				int money = um.getUserList().get(um.userLog).getMoney();
				if (sum > money) {
					System.out.println("금액이 부족합니다.");
				} else {
					um.getUserList().get(um.userLog).setMoney(money - sum);
					im.delUserJangItem(um.getUserList().get(um.userLog));
				}
			} else if (sel == 0) {
				run = false;
			}
		}
	}

	private void shopMenu() {
		boolean run = true;
		while (run) {
			im.printCategory();
			System.out.println("[카테고리] 번호를 입력하세요. [종료. -1]");
			int caID = UserManager.scan.nextInt();
			if (caID == -1) {
				break;
			}
			im.printItemList(caID);
			System.out.println("[아이템] 번호를 입력하세요");
			int itID = UserManager.scan.nextInt();
			int cnt = 0;
			cnt = im.getItemCnt(caID, itID);
			if (cnt != 0) {
				im.addCart(um.getUserList().get(um.userLog).getId(), caID, itID);
			} else {
				System.out.println("개수가 부족합니다.");
			}
		}
	}

	private void itemMenu() {
		boolean run = true;
		while (run) {
			System.out.println("[1.전체 아이템]\n[2.아이템 추가]\n[3.아이템 삭제]\n[0.뒤로가기]");
			int sel = UserManager.scan.nextInt();
			if (sel == 1) {
				im.printItemList();
			} else if (sel == 2) {
				im.addItem();
			} else if (sel == 3) {
				im.delItem();
			} else if (sel == 0) {
				run = false;
			}
		}
	}

	void categoryMenu() {
		boolean run = true;
		while (run) {
			System.out.println("[1.전체카테고리]\n[2.카테고리추가]\n[3.카테고리삭제]\n[0.뒤로가기]");
			int sel = UserManager.scan.nextInt();
			if (sel == 1) {
				im.printCategory();
			} else if (sel == 2) {
				im.printCategory();
				im.addCategory();
			} else if (sel == 3) {
				im.delCategory();
			} else if (sel == 0) {
				run = false;
			}
		}
	}

	void userMenu() {
		boolean run = true;
		while (run) {
			System.out.println("[1.전체유저]\n[2.유저추가]\n[3.유저삭제]\n[0.뒤로가기]");
			int sel = UserManager.scan.nextInt();
			if (sel == 1) {
				um.printUser();
			} else if (sel == 2) {
				um.join();
			} else if (sel == 3) {
				um.withdraw();
			} else if (sel == 0) {
				run = false;
			}
		}
	}
}
