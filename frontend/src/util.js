export function clearCookies() {
    document.cookie.split(";").forEach(cookie => {
        document.cookie = cookie
            .replace(/^ +/, "")
            .replace(/=.*/, `=;expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/`);
    });
}
